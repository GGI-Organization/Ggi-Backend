package com.ggi.domain.service;

import com.ggi.domain.model.Administrator;

import java.util.Optional;

public interface AdministratorService {
    Optional<Administrator> findById(Long administratorId);
}
