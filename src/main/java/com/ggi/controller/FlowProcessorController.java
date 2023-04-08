package com.ggi.controller;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggi.domain.service.AzureConnectService;
import com.ggi.resource.response.PredictionRes;
import com.ggi.resource.response.TasksRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/v1/flow-processor")
public class FlowProcessorController {
    @Autowired
    private AzureConnectService azureConnectService;
    @PostMapping(value = "/bpmn-tasks", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<String> uploadImage(MultipartFile image) {
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
            //Creating the ObjectMapper object
            ObjectMapper mapper = new ObjectMapper();
            TasksRes res = new TasksRes();
            res.setTasks(tasks);
            //Converting the Object to JSONString
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(res);
            return ResponseEntity.status(200).body(jsonString);
        }catch (Exception e){
            return ResponseEntity.status(500).body("Failed to upload image: " + e.getMessage());
        }
    }
}
