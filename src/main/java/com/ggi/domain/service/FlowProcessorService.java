package com.ggi.domain.service;

import com.ggi.domain.model.FlowProcessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FlowProcessorService {

    Page<FlowProcessor> getAll(Pageable pageable);
    FlowProcessor getById(Long id);
    FlowProcessor create(FlowProcessor processor);
    FlowProcessor update(Long id, FlowProcessor processorRequest);
    ResponseEntity<?> delete(Long id);
    ResponseEntity<?> loadDiagram();
}
