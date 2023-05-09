package com.ggi.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggi.model.EStatus;
import com.ggi.payload.dto.RootPredictionDto;
import com.ggi.payload.request.DiagramReq;
import com.ggi.payload.request.TaskReq;
import com.ggi.repository.UserRepository;
import com.ggi.security.jwt.JwtUtils;
import com.ggi.service.interfaces.AzureConnectService;
import com.ggi.payload.response.*;
import com.ggi.service.interfaces.DiagramBPMNService;
import com.ggi.service.interfaces.MockupService;
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
    @Autowired
    private DiagramBPMNService diagramBPMNService;
    @Autowired
    private MockupService mockupService;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/bpmn-tasks", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<?> uploadImage(@RequestParam(name = "image") MultipartFile image, @RequestHeader("Authorization") String token) {
        var res = new DefaultRes<>();
        try {
            // Get user Id
            var username = jwtUtils.getUserNameFromJwtToken(token.split(" ")[1]);
            var user = userRepository.findByEmail(username);
            // Use API Custom Vision
            CompletableFuture<PredictionRes> asyncPredictionRes = azureConnectService.getPredictionFromBPMN(image);
            PredictionRes predictionRes = asyncPredictionRes.get();
            if (predictionRes == null) throw new Exception("Failed process prediction bpmn");
            // Get Bytes from image class prediction
            RootPredictionDto rootClippedImages = azureConnectService.getRootsAboutPrediction(predictionRes, image);
            if (rootClippedImages == null) throw new Exception("Failed process get roots from image");
            // Get tasks from image bytes
            ArrayList<String> tasks = azureConnectService.getOCRFromImageV2(rootClippedImages.getRoots());
            // Task filter proceess
            ArrayList<TaskDetailRes> taskDetailRes = azureConnectService.TaskFilter(tasks);
            TasksRes tasksRes = new TasksRes(taskDetailRes);
            tasksRes.setPath(rootClippedImages.getMainFolder());
            // Pre-save bpmn info
            var tasksReq = new ArrayList<TaskReq>();
            for (var taskRes : tasksRes.getTasks()) {
                tasksReq.add(new TaskReq(taskRes.getTask()));
            }
            DiagramReq diagramReq = new DiagramReq(EStatus.EN_PROCESO.toString(), "", rootClippedImages.getMainFolder(), user.get().getId(), tasksReq);
            diagramBPMNService.create(diagramReq);

            res = new DefaultRes<>("", false);
            res.setResult(tasksRes);
            return ResponseEntity.status(200).body(res);
        } catch (Exception e) {
            res = new DefaultRes<>("Failed to upload image: " + e.getMessage(), true);
            return ResponseEntity.status(500).body(res);
        }
    }

    @PostMapping(value = "/mockup-tasks", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<?> uploadMockups(@RequestHeader("Authorization") String token, @RequestParam("mockups") MultipartFile[] mockups, @RequestParam("tasks") String tasks) {
        var res = new DefaultRes<>();
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

            // Save info from mockups relation with user
            // Get user Id
            var username = jwtUtils.getUserNameFromJwtToken(token.split(" ")[1]);
            var user = userRepository.findByEmail(username);
            // Save all image mockups
            String nameFolder = azureConnectService.saveMockupImages(mockups);
            var isAdded = mockupService.create(nameFolder, user.get().getId(), predictionMockupRes);
            if (!isAdded) throw new Exception("Error to save info mockup group");

            res = new DefaultRes<>("", false);
            res.setResult(predictionMockupRes);
            return ResponseEntity.status(200).body(res);
        } catch (Exception e) {
            res = new DefaultRes<>("Failed to upload image: " + e.getMessage(), true);
            return ResponseEntity.status(500).body(res);
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
