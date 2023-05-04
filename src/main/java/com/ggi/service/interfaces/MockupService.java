package com.ggi.service.interfaces;

import com.ggi.model.Mockup;
import com.ggi.payload.request.MockupDetailReq;
import com.ggi.payload.request.MockupReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

public interface MockupService {
    Page<Mockup> getAll(Pageable pageable, Long userId);
    Optional<Mockup> getById(Long id);
    ArrayList<Mockup> create(MockupReq mockupReq);
    //Mockup update(Long id, Mockup mockup);
    ResponseEntity<?> delete(Long id);
}
