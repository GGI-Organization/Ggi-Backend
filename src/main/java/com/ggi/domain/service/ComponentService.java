package com.ggi.domain.service;

import com.ggi.domain.model.Component;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ComponentService {
    Page<Component> getAll(Pageable pageable);
    Page<Component> getAllComponentsByDiagramId(Long diagramId, Pageable pageable);
    Component getComponentByIdAndDiagramId(Long id, Long diagramId);
    Component getById(Long id);
    Component create(Long diagramId, Component component);
    Component update(Long id, Long diagramId, Component componentDetails);
    ResponseEntity<?> delete(Long id, Long diagramId);
}
