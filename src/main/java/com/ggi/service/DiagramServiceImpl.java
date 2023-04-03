package com.ggi.service;

import com.ggi.domain.model.Component;
import com.ggi.domain.model.Diagram;
import com.ggi.domain.repository.ComponentRepository;
import com.ggi.domain.repository.DiagramRepository;
import com.ggi.domain.service.DiagramService;
import com.ggi.exception.ResourceNotFoundException;
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
        return diagramRepository.findAll(pageable);
    }

    @Override
    public Diagram getById(Long id) {
        return diagramRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Diagram not found wit Id" + id));
    }

    @Override
    public Diagram create(Diagram diagram) {
        return diagramRepository.save(diagram);
    }

    @Override
    public Diagram update(Long id, Diagram diagramRequest) {
        Diagram diagram = diagramRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diagram", "Id", id));

        diagram.setId(diagramRequest.getId())
                .setStatus(diagramRequest.getStatus());

        return diagramRepository.save(diagram);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Diagram diagram = diagramRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diagram", "Id", id));

        diagramRepository.delete(diagram);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Diagram> getAllByStatus(boolean status) {
        return null;
    }

    @Override
    public Diagram assignComponentById(Long id, Long componentId) {
        Component component = componentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Component", "Id", componentId));
        return diagramRepository.findById(id).map(
                        diagram -> diagramRepository.save(diagram.addComponent(component)))
                .orElseThrow(() -> new ResourceNotFoundException("Diagram", "Id", id));
    }

    @Override
    public Diagram UnAssignComponentById(Long id, Long componentId) {
        Component component = componentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Component", "Id", componentId));
        return diagramRepository.findById(id).map(
                        diagram -> diagramRepository.save(diagram.removeComponent(component)))
                .orElseThrow(() -> new ResourceNotFoundException("Diagram", "Id", id));
    }
}
