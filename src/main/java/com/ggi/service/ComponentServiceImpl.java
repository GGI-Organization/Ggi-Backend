package com.ggi.service;

import com.ggi.domain.model.Component;
import com.ggi.domain.repository.ComponentRepository;
import com.ggi.domain.repository.DiagramRepository;
import com.ggi.domain.service.ComponentService;
import com.ggi.exception.ResourceNotFoundException;
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
        return componentRepository.findAll(pageable);
    }

    @Override
    public Page<Component> getAllComponentsByDiagramId(Long diagramId, Pageable pageable) {
        return componentRepository.findByDiagramId(diagramId, pageable);
    }

    @Override
    public Component getComponentByIdAndDiagramId(Long id, Long diagramId) {
        return componentRepository.findByIdAndDiagramId(id, diagramId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Component not found with Id" + id +
                                " and DiagramId " + diagramId));
    }

    @Override
    public Component getById(Long id) {
        return componentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Component not found wit Id" + id));
    }

    @Override
    public Component create(Long diagramId, Component component) {
        return diagramRepository.findById(diagramId).map(diagram -> {
            component.setDiagram(diagram);
            return componentRepository.save(component);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Diagram", "Id", diagramId));
    }

    @Override
    public Component update(Long id, Long diagramId, Component componentDetails) {
        if (!diagramRepository.existsById(diagramId))
            throw new ResourceNotFoundException("Diagram", "Id", diagramId);

        return componentRepository.findById(id).map(component -> {
            component.setDescription(componentDetails.getDescription())
                    .setTag(componentDetails.getTag());
            return componentRepository.save(component);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Component", "Id", id));
    }

    @Override
    public ResponseEntity<?> delete(Long id, Long diagramId) {
        if(!diagramRepository.existsById(diagramId))
            throw new ResourceNotFoundException("Diagram", "Id", diagramId);

        return componentRepository.findById(id).map(component -> {
            componentRepository.delete(component);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Component", "Id", id));
    }
}
