package com.ggi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggi.domain.service.AzureConnectService;
import com.ggi.resource.response.BoundingBoxRes;
import com.ggi.resource.response.OCRRes;
import com.ggi.resource.response.PredictionRes;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class AzureConnectServiceImpl implements AzureConnectService {
    @Override
    public CompletableFuture<PredictionRes> getPredictionFromBPMN(MultipartFile image) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                byte[] fileBytes = image.getBytes();
                // Construct the HTTP request
                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .uri(URI.create("https://southcentralus.api.cognitive.microsoft.com/customvision/v3.0/Prediction/403c5403-8bd2-4e42-a6b4-41bdb40f7794/detect/iterations/Iteration3/image"))
                        .header("Content-Type", "application/octet-stream")
                        .header("Prediction-key", "55ba7b5e720e43ffae34de0e66b68a15")
                        .POST(HttpRequest.BodyPublishers.ofByteArray(fileBytes))
                        .build();

                // Send the HTTP request and get the response
                HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    // Convert result JSON to PredictionRes Class
                    //File outputFile = new File("src/main/resources/bpmn.jpg");
                    //try(OutputStream os = new FileOutputStream(outputFile)){
                    //    os.write(image.getBytes());
                    //}
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(response.body(), PredictionRes.class);
                } else {
                    return null;
                }
            } catch (Exception e) {
                return null;
            }
        });
    }

    @Override
    public PredictionRes getPredictionFromMockup(MultipartFile imageMockup) {
        return null;
    }

    public String createDirectory() {
        String nameFolder = UUID.randomUUID().toString();
        String PATH = "src/main/resources/" + nameFolder;
        File directory = new File(PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
        return PATH;
    }

    public void saveMainBPMN(MultipartFile image, String path) {
        try {
            File outputFile = new File(path + "/" + "bpmn.png");
            try (OutputStream os = new FileOutputStream(outputFile)) {
                os.write(image.getBytes());
            }
        } catch (Exception e) {
            System.out.println("ERROR SAVE MAIN BPMN " + e.getMessage());
        }
    }

    public BufferedImage getClippedImage(BoundingBoxRes params, MultipartFile image) {
        //BoundingBoxRes boundingBoxRes = predictionTags.getBoundingBox();
        try {
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(image.getBytes()));
            int imageWidth = bufferedImage.getWidth();
            int imageHeight = bufferedImage.getHeight();
            int x1 = (int) (params.getLeft() * imageWidth);
            int y1 = (int) (params.getTop() * imageHeight);
            int x2 = (int) ((params.getLeft() + params.getWidth()) * imageWidth);
            int y2 = (int) ((params.getTop() + params.getHeight()) * imageHeight);
            return bufferedImage.getSubimage(x1, y1, x2 - x1, y2 - y1);
        } catch (Exception e) {
            System.out.println("ERROR CLIPPED IMAGE " + e.getMessage());
            return null;
        }

    }

    @Override
    public ArrayList<String> getRootsAboutPrediction(PredictionRes predictionRes, MultipartFile image) {
        try {
            ArrayList<String> rootImageClipped = new ArrayList<>();
            String PATH = createDirectory();
            // Save bpmn image
            saveMainBPMN(image, PATH);
            for (var predictionTags : predictionRes.getPredictions()) {
                if (predictionTags.getTagName().equals("Tarea") && predictionTags.getProbability() >= 0.85) {
                    // Crop the image based on the provided coordinates
                    BufferedImage clippedImage = getClippedImage(predictionTags.getBoundingBox(), image);

                    // Save clipped image to new folder
                    String fileName = PATH + "/" + RandomStringUtils.random(8, "0123456789abcdef") + "-" + predictionTags.getProbability() + ".png";
                    File outputFile = new File(fileName);
                    ImageIO.write(clippedImage, "png", outputFile);
                    // Add root images
                    rootImageClipped.add(fileName);
                }
            }
            return rootImageClipped;
        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
            return null;
        }
    }

    @Override
    public CompletableFuture<List<String>> getOCRFromImage(List<byte[]> bytesFromBPMN) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<String> tasks = new ArrayList<String>();
            try {
                // Construct the HTTP request
                for (var bytesFromImage : bytesFromBPMN) {

                    // Encode the image bytes as a Base64 string
                    String base64Image = Base64.getEncoder().encodeToString(bytesFromImage);
                    // Build the request body
                    String requestBody = "{\"url\":\"data:image/png;base64," + base64Image + "\"}";

                    HttpClient httpClient = HttpClient.newHttpClient();
                    HttpRequest httpRequest = HttpRequest.newBuilder()
                            .uri(URI.create("https://instance-ocr-bpmn.cognitiveservices.azure.com/vision/v3.2/ocr?language=es&detectOrientation=true&model-version=latest"))
                            .header("Content-Type", "application/json")
                            .header("Ocp-Apim-Subscription-Key", "108584415a4d4f94bbbe4d5bad99dea3")
                            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                            .build();

                    // Send the HTTP request and get the response
                    HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                    if (response.statusCode() == 200) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        OCRRes ocrRes = objectMapper.readValue(response.body(), OCRRes.class);
                        var task = "";
                        for (var region : ocrRes.getRegions()) {
                            for (var line : region.getLines()) {
                                for (var word : line.getWords()) {
                                    task = task + word + " ";
                                }
                            }
                        }
                        tasks.add(task);
                    } else {
                        System.out.println("ERROR SERVICE OCR" + response.statusCode() + response.body());
                    }
                }
                return tasks;
            } catch (Exception e) {
                System.out.println("ERROR PROCESS GET TASKS " + e.getMessage());
                return tasks;
            }
        });
    }

    @Override
    public ArrayList<String> getOCRFromImageV2(ArrayList<String> rootImagesClipped) {
        try {
            ArrayList<String> tasks = new ArrayList<String>();
            for (var root : rootImagesClipped) {
                // Step 1: Read the image file and convert to byte array
                File imageFile = new File(root);
                FileInputStream inputStream = new FileInputStream(imageFile);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                byte[] imageBytes = outputStream.toByteArray();

                // Step 2: Create the HttpClient object
                HttpClient client = HttpClient.newBuilder().build();

                // Step 3: Create the HttpRequest object
                String subscriptionKey = "108584415a4d4f94bbbe4d5bad99dea3";
                String endpoint = "https://instance-ocr-bpmn.cognitiveservices.azure.com/vision/v3.2/ocr?language=es&detectOrientation=true&model-version=latest";
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endpoint))
                        .header("Content-Type", "application/octet-stream")
                        .header("Ocp-Apim-Subscription-Key", subscriptionKey)
                        .POST(HttpRequest.BodyPublishers.ofByteArray(imageBytes))
                        .build();

                // Step 4: Send the HTTP request and receive the response
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // Step 5: Parse the response and extract relevant information
                String responseBody = response.body();
                if (response.statusCode() == 200) {
                    System.out.println("TASK => " + responseBody);
                    ObjectMapper objectMapper = new ObjectMapper();
                    OCRRes ocrRes = objectMapper.readValue(responseBody, OCRRes.class);
                    String task = "";
                    for (var region : ocrRes.getRegions()) {
                        for (var line : region.getLines()) {
                            for (var word : line.getWords()) {
                                task = task + word.getText() + " ";
                            }
                        }
                    }
                    System.out.println("TASK => " + task);
                    tasks.add(task);
                } else {
                    System.out.println("ERROR SERVICE OCR" + response.statusCode() + response.body());
                }
            }
            return tasks;
        } catch (Exception e) {
            System.out.println("ERROR GET OCR " + e.getMessage());
            return null;
        }

    }
}
