package com.ggi.service;

import com.ggi.domain.model.Diagram;
import com.ggi.domain.repository.ComponentRepository;
import com.ggi.domain.repository.DiagramRepository;
import com.ggi.domain.service.DiagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DiagramServiceImpl implements DiagramService  {

    @Autowired
    private DiagramRepository diagramRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @Override
    public Page<Diagram> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Diagram getById(Long id) {
        return null;
    }

    @Override
    public Diagram create(Diagram diagram) {
        return null;
    }

    @Override
    public Diagram update(Long id, Diagram diagramRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }

    @Override
    public Page<Diagram> getAllByStatus(boolean status) {
        return null;
    }

    @Override
    public Diagram assignComponentById(Long diagramId, Long componentId) {
        return null;
    }

    @Override
    public Diagram UnAssignComponentById(Long diagramId, Long componentId) {
        return null;
    }
}
