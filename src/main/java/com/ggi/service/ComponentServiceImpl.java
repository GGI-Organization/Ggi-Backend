package com.ggi.service;

import com.ggi.domain.model.Component;
import com.ggi.domain.repository.ComponentRepository;
import com.ggi.domain.repository.DiagramRepository;
import com.ggi.domain.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ComponentServiceImpl implements ComponentService {

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private DiagramRepository diagramRepository;

    @Override
    public Page<Component> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Component> getAllComponentsByDiagramId(Long diagramId, Pageable pageable) {
        return null;
    }

    @Override
    public Component getById(Long id) {
        return null;
    }

    @Override
    public Component create(Component component) {
        return null;
    }

    @Override
    public Component update(Long id, Component componentRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }

    @Override
    public Component getByTag(String tag) {
        return null;
    }
}
