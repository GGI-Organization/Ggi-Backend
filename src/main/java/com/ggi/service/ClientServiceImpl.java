package com.ggi.service;

import com.ggi.domain.model.Client;
import com.ggi.domain.repository.ClientRepository;
import com.ggi.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Optional<Client> findById(Long clientId) {
        return clientRepository.findById(clientId);
    }
}
