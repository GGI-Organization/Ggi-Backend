package com.ggi.service.interfaces;

import com.ggi.model.Mockup;
import com.ggi.model.MockupGroup;
import com.ggi.payload.request.MockupDetailReq;
import com.ggi.payload.request.MockupReq;
import com.ggi.payload.response.PredictionMockupRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

public interface MockupService {
    Page<MockupGroup> getAll(Pageable pageable, Long userId);
    Optional<Mockup> getById(Long id);
    boolean create(String nameFolder, Long userId, PredictionMockupRes predictionMockupRes);
    //Mockup update(Long id, Mockup mockup);
    ResponseEntity<?> delete(Long id);
}
