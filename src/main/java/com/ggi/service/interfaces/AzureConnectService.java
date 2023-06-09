package com.ggi.service.interfaces;

import com.ggi.payload.dto.RootPredictionDto;
import com.ggi.payload.response.BoundingBoxRes;
import com.ggi.payload.response.MockupRes;
import com.ggi.payload.response.PredictionRes;
import com.ggi.payload.response.TaskDetailRes;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AzureConnectService {
    CompletableFuture<PredictionRes> getPredictionFromBPMN(MultipartFile image);
    CompletableFuture<PredictionRes> getPredictionFromMockup(MultipartFile imageMockup);
    RootPredictionDto getRootsAboutPrediction(PredictionRes predictionRes, MultipartFile image) throws Exception;
    String saveMockupImages(MultipartFile[] images);
    CompletableFuture<List<String>> getOCRFromImage(List<byte[]> bytesFromBPMN);
    ArrayList<String> getOCRFromImageV2(ArrayList<String> rootImagesClipped) throws Exception;
    ArrayList<TaskDetailRes> TaskFilter(ArrayList<String> tasks) throws Exception;
    void saveMainBPMN(MultipartFile image, String path);
    String[] createDirectory();
    BufferedImage getClippedImage(BoundingBoxRes params, MultipartFile image);
    ArrayList<MockupRes> getComponentDetails(ArrayList<MultipartFile> mockupsImg, ArrayList<TaskDetailRes> tasks, ArrayList<PredictionRes> predictionsRes);
}