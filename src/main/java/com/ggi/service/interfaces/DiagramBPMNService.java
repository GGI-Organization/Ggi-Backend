package com.ggi.service.interfaces;

import com.ggi.model.DiagramBPMN;
import com.ggi.payload.request.DiagramReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface DiagramBPMNService {
    Page<DiagramBPMN> getAll(Pageable pageable, Long userId);
    Optional<DiagramBPMN> getById(Long id);
    DiagramBPMN create(DiagramReq diagramReq);
    //DiagramBPMN update(Long id, DiagramBPMN diagramBPMN);
    ResponseEntity<?> delete(Long id);
}
