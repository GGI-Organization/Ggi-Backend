package com.ggi.service;

import com.ggi.domain.model.FlowProcessor;
import com.ggi.domain.repository.FlowProcessorRepository;
import com.ggi.domain.service.FlowProcessorService;
import com.ggi.exception.ResourceNotFoundException;
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
        return flowProcessorRepository.findAll(pageable);
    }

    @Override
    public FlowProcessor getById(Long id) {
        return flowProcessorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "FlowProcessor not found wit Id" + id));
    }

    @Override
    public FlowProcessor create(FlowProcessor processor) {
        return flowProcessorRepository.save(processor);
    }

    @Override
    public FlowProcessor update(Long id, FlowProcessor processorRequest) {
        FlowProcessor flowProcessor = flowProcessorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FlowProcessor", "Id", id));

        flowProcessor.setId(processorRequest.getId())
                .setStatus(processorRequest.getStatus());

        return flowProcessorRepository.save(flowProcessor);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        FlowProcessor flowProcessor = flowProcessorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FlowProcessor", "Id", id));

        flowProcessorRepository.delete(flowProcessor);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> loadDiagram() {
        return null;
    }
}
