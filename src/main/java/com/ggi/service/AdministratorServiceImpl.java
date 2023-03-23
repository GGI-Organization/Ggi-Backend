package com.ggi.service;

import com.ggi.domain.model.Administrator;
import com.ggi.domain.repository.AdministratorRepository;
import com.ggi.domain.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public Optional<Administrator> findById(Long administratorId) {
        return administratorRepository.findById(administratorId);
    }
}
