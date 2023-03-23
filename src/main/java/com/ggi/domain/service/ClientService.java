package com.ggi.domain.service;

import com.ggi.domain.model.Client;

import java.util.Optional;

public interface ClientService {
    Optional<Client> findById(Long clientId);
}
