package com.ggi.domain.service;

import com.ggi.domain.model.FlowProcessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FlowProcessorService {

    Page<FlowProcessor> getAllProcessors(Pageable pageable);

    FlowProcessor getProcessorById(Long id);

    FlowProcessor createProcessor(FlowProcessor processor);

    FlowProcessor updateProcessor(Long id, FlowProcessor processorRequest);

    ResponseEntity<?> deleteProcessor(Long id);

    ResponseEntity<?> loadDiagram();
}
