package com.ggi.service.implement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggi.payload.response.*;
import com.ggi.service.interfaces.AzureConnectService;
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
                        .uri(URI.create("https://inspry20220265-prediction.cognitiveservices.azure.com/customvision/v3.0/Prediction/54b47c51-a382-4a9a-8c39-96a85e7f2e31/detect/iterations/Iteration3/image"))
                        .header("Content-Type", "application/octet-stream")
                        .header("Prediction-key", "e1d75e363f844e6ca916e6e35954a74f")
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
    public CompletableFuture<PredictionRes> getPredictionFromMockup(MultipartFile imageMockup) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                byte[] fileBytes = imageMockup.getBytes();
                // Construct the HTTP request
                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .uri(URI.create("https://inspry20220265-prediction.cognitiveservices.azure.com/customvision/v3.0/Prediction/4087032c-fdf4-477a-83e7-9e666c879133/detect/iterations/Iteration2/image"))
                        .header("Content-Type", "application/octet-stream")
                        .header("Prediction-key", "e1d75e363f844e6ca916e6e35954a74f")
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
    public ArrayList<MockupRes> getComponentDetails(ArrayList<MultipartFile> mockupsImg, ArrayList<TaskDetailRes> tasks, ArrayList<PredictionRes> predictionsRes) {
        try {
            ArrayList<MockupRes> mockups = new ArrayList<>();
            for (int i = 0; i < tasks.size(); i++) {
                // Cada prediction es de un mockup
                TaskDetailRes task = tasks.get(i);
                PredictionRes predictionRes = predictionsRes.get(i);
                ArrayList<ComponentRes> components = new ArrayList<>();
                for (var predictionTags : predictionRes.getPredictions()) {
                    if (predictionTags.getProbability() >= 0.75) {
                        var params = predictionTags.getBoundingBox();
                        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(mockupsImg.get(i).getBytes()));
                        int imageWidth = bufferedImage.getWidth();
                        int imageHeight = bufferedImage.getHeight();
                        System.out.println(predictionTags.getTagName() + " - " + params.getLeft() + " - " + params.getTop());
                        int x1 = (int) (params.getLeft() * 1680);
                        int y1 = (int) (params.getTop() * 1080);
                        int x2 = (int) ((params.getLeft() + params.getWidth()) * 1680);
                        int y2 = (int) ((params.getTop() + params.getHeight()) * 1080);
                        System.out.println(predictionTags.getTagName() + " - " + x1 + " - " + y2 + " - " + (x2 - x1) + " - " + (y2 - y1));
                        var component = new ComponentRes(predictionTags.getTagName(), x1, y1, x2 - x1, y2 - y1);
                        components.add(component);
                    }
                }
                MockupRes mockupRes = new MockupRes(components, task);
                mockups.add(mockupRes);
            }
            return mockups;
        } catch (Exception e) {
            System.out.println("ERROR GET SIZE COMPONENT " + e.getMessage());
            return null;
        }
    }

    @Override
    public String createDirectory() {
        String nameFolder = UUID.randomUUID().toString();
        String PATH = "src/main/resources/" + nameFolder;
        File directory = new File(PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
        return PATH;
    }

    @Override
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

    @Override
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
            //saveMainBPMN(image, PATH);
            for (var predictionTags : predictionRes.getPredictions()) {
                if (predictionTags.getTagName().equals("tarea-usuario") && predictionTags.getProbability() >= 0.70) {
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
                        tasks.add(task.trim());
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
    public ArrayList<TaskDetailRes> TaskFilter(ArrayList<String> tasks) {

        // Datos de categorias
        HashMap<String, ArrayList<String>> categories = new HashMap<>();
        categories.put("create", new ArrayList<>(Arrays.asList("crea", "agrega", "construir", "publica", "añad", "registr")));
        categories.put("read", new ArrayList<>(Arrays.asList("lee", "ver", "revis", "listar", "busca", "mostrar", "examinar")));
        categories.put("update", new ArrayList<>(Arrays.asList("actualiza", "modifica", "edita", "cambia", "corregir", "corrige")));
        categories.put("delete", new ArrayList<>(Arrays.asList("elimina", "borra", "suprim", "descarta", "remover", "deshacer")));

        // Obtener las oraciones relacionadas al desarrollo de software
        ArrayList<TaskDetailRes> taskDetailRes = new ArrayList<>();
        boolean pass = true;
        for (String sentence : tasks) {
            pass = true;
            for (String category : categories.keySet()) {
                ArrayList<String> keywords = categories.get(category);
                for (String keyword : keywords) {
                    if (sentence.toLowerCase().contains(keyword.toLowerCase())) {
                        taskDetailRes.add(new TaskDetailRes(sentence, category, keyword));
                        pass = false;
                        break;
                    }
                }
                if (!pass)
                    break;
            }
        }
        return taskDetailRes;
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
