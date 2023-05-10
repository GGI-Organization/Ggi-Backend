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

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class DiagramBPMNServiceImpl implements DiagramBPMNService {

    @Autowired
    private DiagramBPMNRepository diagramBPMNRepository;

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public Page<DiagramBPMN> getAll(Pageable pageable, String name, Long userId) {
        List<DiagramBPMN> result;
        if (name == null || name.isEmpty()) {
            result = diagramBPMNRepository.findAll(pageable).stream().filter(diagramBPMN -> Objects.equals(diagramBPMN.getUserId(), userId) && diagramBPMN.getStatus() == EStatus.ACTIVO).toList();
        } else {
            result = diagramBPMNRepository.findAll(pageable).stream().filter(diagramBPMN -> Objects.equals(diagramBPMN.getUserId(), userId) && diagramBPMN.getStatus() == EStatus.ACTIVO && diagramBPMN.getName().toLowerCase().contains(name.trim().toLowerCase())).toList();
        }
        return new PageImpl<DiagramBPMN>(result);
    }

    @Override
    public Optional<DiagramBPMN> getById(Long id) {
        return diagramBPMNRepository.findById(id);
    }

    @Override
    public DiagramBPMN create(DiagramReq diagramReq) {
        var newDiagramBPMN = new DiagramBPMN(diagramReq.getUserId(), EStatus.valueOf(diagramReq.getStatus()), diagramReq.getName(), diagramReq.getPath());
        diagramBPMNRepository.save(newDiagramBPMN);
        var newTasks = diagramReq.getTasks().stream().map(task -> new Task(task.getName(), newDiagramBPMN)).toList();
        taskRepository.saveAll(newTasks);
        return newDiagramBPMN;
    }

    @Override
    public boolean update(String name, String path, Long userId) {
        var diagramBPMN = diagramBPMNRepository.findByUserIdAndPath(userId, path);
        if (diagramBPMN.isEmpty()) return false;
        diagramBPMN.get().setName(name);
        diagramBPMN.get().setStatus(EStatus.ACTIVO);
        diagramBPMNRepository.save(diagramBPMN.get());
        return true;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        diagramBPMNRepository.deleteById(id);
        return null;
    }
}
