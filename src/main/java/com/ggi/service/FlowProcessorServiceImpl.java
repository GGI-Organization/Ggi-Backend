package com.ggi.service;

import com.ggi.domain.model.FlowProcessor;
import com.ggi.domain.repository.FlowProcessorRepository;
import com.ggi.domain.service.FlowProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FlowProcessorServiceImpl implements FlowProcessorService {

    @Autowired
    private FlowProcessorRepository flowProcessorRepository;

    @Override
    public Page<FlowProcessor> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public FlowProcessor getById(Long id) {
        return null;
    }

    @Override
    public FlowProcessor create(FlowProcessor processor) {
        return null;
    }

    @Override
    public FlowProcessor update(Long id, FlowProcessor processorRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> loadDiagram() {
        return null;
    }
}
