package com.ggi.domain.service;

import com.ggi.resource.response.PredictionRes;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AzureConnectService {
    CompletableFuture<PredictionRes> getPredictionFromBPMN(MultipartFile image);
    PredictionRes getPredictionFromMockup(MultipartFile imageMockup);
    ArrayList<String> getRootsAboutPrediction(PredictionRes predictionRes, MultipartFile image);
    CompletableFuture<List<String>> getOCRFromImage(List<byte[]> bytesFromBPMN);
    ArrayList<String> getOCRFromImageV2(ArrayList<String> rootImagesClipped);
}
