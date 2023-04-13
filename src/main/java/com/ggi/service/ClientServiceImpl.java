package com.ggi.service;

import com.ggi.domain.model.Client;
import com.ggi.domain.repository.ClientRepository;
import com.ggi.domain.service.ClientService;
import com.ggi.exception.ResourceNotFoundException;
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
        return clientRepository.findAll(pageable);
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client not found wit Id" + id));
    }

    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Long id, Client clientRequest) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator", "Id", id));

        client.setAccountSettings(clientRequest.getAccountSettings())
                .setId(clientRequest.getId())
                .setPassword(clientRequest.getPassword())
                .setLoginStatus(clientRequest.getLoginStatus())
                .setUserName(clientRequest.getUserName())
                .setEmail(clientRequest.getEmail())
                .setRegisterDate(clientRequest.getRegisterDate());

        return clientRepository.save(client);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "Id", id));

        clientRepository.delete(client);
        return ResponseEntity.ok().build();
    }
}
