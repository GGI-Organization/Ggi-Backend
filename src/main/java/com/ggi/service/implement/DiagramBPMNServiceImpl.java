package com.ggi.service.implement;

import com.ggi.model.DiagramBPMN;
import com.ggi.model.EStatus;
import com.ggi.model.Task;
import com.ggi.payload.dto.DiagramBPMNDto;
import com.ggi.payload.request.DiagramReq;
import com.ggi.payload.request.TaskReq;
import com.ggi.repository.ComponentUIRepository;
import com.ggi.repository.DiagramBPMNRepository;
import com.ggi.repository.TaskRepository;
import com.ggi.service.interfaces.DiagramBPMNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiagramBPMNServiceImpl implements DiagramBPMNService {

    @Autowired
    private DiagramBPMNRepository diagramBPMNRepository;

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public Page<DiagramBPMN> getAll(Pageable pageable, Long userId) {
        var result = diagramBPMNRepository.findAll(pageable).stream().filter(diagramBPMN -> Objects.equals(diagramBPMN.getUserId(), userId)).toList();
        return new PageImpl<DiagramBPMN>(result);
    }

    @Override
    public Optional<DiagramBPMN> getById(Long id) {
        return diagramBPMNRepository.findById(id);
    }

    @Override
    public DiagramBPMN create(DiagramReq diagramReq) {
        var newTasks = diagramReq.getTasks().stream().map(task -> new Task(task.getName())).toList();
        taskRepository.saveAll(newTasks);
        Set<Task> targetSet = new HashSet<Task>(newTasks);
        var newDiagramBPMN = new DiagramBPMN(diagramReq.getUserId(), EStatus.ACTIVO, diagramReq.getName(), diagramReq.getPath(), targetSet);
        diagramBPMNRepository.save(newDiagramBPMN);
        return newDiagramBPMN;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        diagramBPMNRepository.deleteById(id);
        return null;
    }
}
