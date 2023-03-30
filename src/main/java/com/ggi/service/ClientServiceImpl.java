package com.ggi.service;

import com.ggi.domain.model.Client;
import com.ggi.domain.repository.ClientRepository;
import com.ggi.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public Page<Client> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Client getById(Long id) {
        return null;
    }

    @Override
    public Client create(Client client) {
        return null;
    }

    @Override
    public Client update(Long id, Client client) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }
}
