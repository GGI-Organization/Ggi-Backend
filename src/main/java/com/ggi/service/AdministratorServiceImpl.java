package com.ggi.service;

import com.ggi.domain.model.Administrator;
import com.ggi.domain.repository.AdministratorRepository;
import com.ggi.domain.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public Page<Administrator> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Administrator getById(Long id) {
        return null;
    }

    @Override
    public Administrator create(Administrator administrator) {
        return null;
    }

    @Override
    public Administrator update(Long id, Administrator administrator) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }
}
