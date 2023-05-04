package com.ggi.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggi.service.interfaces.AzureConnectService;
import com.ggi.payload.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/flow-processor")
public class FlowProcessorController {
    @Autowired
    private AzureConnectService azureConnectService;

    @PostMapping(value = "/bpmn-tasks", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<String> uploadImage(@RequestParam(name = "image") MultipartFile image) {
        try {
            // Use API Custom Vision
            CompletableFuture<PredictionRes> asyncPredictionRes = azureConnectService.getPredictionFromBPMN(image);
            PredictionRes predictionRes = asyncPredictionRes.get();
            if (predictionRes == null) throw new Exception("Failed process prediction bpmn");
            // Get Bytes from image class prediction
            ArrayList<String> rootClippedImages = azureConnectService.getRootsAboutPrediction(predictionRes, image);
            if (rootClippedImages == null) throw new Exception("Failed process get roots from image");
            // Get tasks from image bytes
            ArrayList<String> tasks = azureConnectService.getOCRFromImageV2(rootClippedImages);
            // Task filter proceess
            ArrayList<TaskDetailRes> taskDetailRes = azureConnectService.TaskFilter(tasks);
            TasksRes res = new TasksRes(taskDetailRes);
            //Creating the ObjectMapper object
            ObjectMapper mapper = new ObjectMapper();
            //Converting the Object to JSONString
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(res);
            return ResponseEntity.status(200).body(jsonString);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload image: " + e.getMessage());
        }
    }

    @PostMapping(value = "/mockup-tasks", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<String> uploadMockups(@RequestParam("mockups") MultipartFile[] mockups, @RequestParam("tasks") String tasks) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TasksRes tasksRes = objectMapper.readValue(tasks, TasksRes.class);
            var taskMockups = tasksRes.getTasks();
            // Use API Custom Vision
            ArrayList<PredictionRes> predictionsRes = new ArrayList<>();
            ArrayList<MultipartFile> mockupsImage = new ArrayList<>();
            for (var image : mockups) {
                CompletableFuture<PredictionRes> asyncPredictionRes = azureConnectService.getPredictionFromMockup(image);
                PredictionRes predictionRes = asyncPredictionRes.get();
                if (predictionRes == null) throw new Exception("Failed process prediction mockup");
                mockupsImage.add(image);
                predictionsRes.add(predictionRes);
            }
            // Get Components structure from mockups
            ArrayList<MockupRes> getComponentsFromMockups = azureConnectService.getComponentDetails(mockupsImage, taskMockups, predictionsRes);
            //Converting the Object to JSONString
            PredictionMockupRes predictionMockupRes = new PredictionMockupRes(getComponentsFromMockups);
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(predictionMockupRes);
            return ResponseEntity.status(200).body(jsonString);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload image: " + e.getMessage());
        }
    }

    @GetMapping(value = "/react-zip")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<FileSystemResource> reactZIP() {
        try {
            // Ruta donde se encuentra el archivo ZIP en el servidor
            String pathFile = "src/main/resources/react-template.zip";

            // Crea un objeto File con la ruta del archivo
            File file = new File(pathFile);

            // Crea un objeto FileSystemResource para leer el archivo del sistema de archivos
            FileSystemResource fileSystemResource = new FileSystemResource(file);

            // Crea los encabezados de la respuesta HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "react-template.zip");

            // Retorna la respuesta HTTP con el archivo ZIP y los encabezados
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(fileSystemResource.contentLength())
                    .body(fileSystemResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
