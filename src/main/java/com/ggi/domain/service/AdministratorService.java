package com.ggi.domain.service;

import com.ggi.domain.model.Administrator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface AdministratorService {
    Page<Administrator> getAll(Pageable pageable);
    Administrator getById(Long id);
    Administrator create(Administrator administrator);
    Administrator update(Long id, Administrator administrator);
    ResponseEntity<?> delete(Long id);
}
