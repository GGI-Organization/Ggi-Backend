package com.ggi.service.interfaces;

import com.ggi.model.Mockup;
import com.ggi.model.MockupGroup;
import com.ggi.payload.request.MockupDetailReq;
import com.ggi.payload.request.MockupReq;
import com.ggi.payload.response.PredictionMockupRes;
import com.ggi.payload.response.TasksRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

public interface MockupService {
    Page<MockupGroup> getAll(Pageable pageable, String name, Long userId);
    Optional<Mockup> getById(Long id);
    boolean create(String nameFolder, Long userId, PredictionMockupRes predictionMockupRes, TasksRes taskRes);
    boolean update(String name, String path, Long userId);
    ResponseEntity<?> delete(Long id);
}
