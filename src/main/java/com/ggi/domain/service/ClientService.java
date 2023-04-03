package com.ggi.domain.service;

import com.ggi.domain.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface ClientService {
    Page<Client> getAll(Pageable pageable);
    Client getById(Long id);
    Client create(Client client);
    Client update(Long id, Client clientRequest);
    ResponseEntity<?> delete(Long id);
}
