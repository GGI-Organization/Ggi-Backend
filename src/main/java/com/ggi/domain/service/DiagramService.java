package com.ggi.domain.service;

import com.ggi.domain.model.Diagram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface DiagramService {
    Page<Diagram> getAll(Pageable pageable);
    Diagram getById(Long id);
    Diagram create(Diagram diagram);
    Diagram update(Long id, Diagram diagramRequest);
    ResponseEntity<?> delete(Long id);
    Page<Diagram> getAllByStatus(boolean status);

    Diagram assignComponentById(Long id, Long componentId);
    Diagram UnAssignComponentById(Long id, Long componentId);
}
