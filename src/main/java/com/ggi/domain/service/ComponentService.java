package com.ggi.domain.service;

import com.ggi.domain.model.Component;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ComponentService {
    Page<Component> getAll(Pageable pageable);
    Page<Component> getAllComponentsByDiagramId(Long diagramId, Pageable pageable);
    Component getById(Long id);
    Component create(Component component);
    Component update(Long id, Component componentRequest);
    ResponseEntity<?> delete(Long id);
    Component getByTag(String tag);
}
