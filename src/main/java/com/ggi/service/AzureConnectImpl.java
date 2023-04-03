package com.ggi.service;

import com.ggi.domain.model.Administrator;
import com.ggi.domain.model.AzureConnect;
import com.ggi.domain.repository.AzureConnectRepository;
import com.ggi.domain.service.AzureConnectService;
import com.ggi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AzureConnectImpl implements AzureConnectService {

    @Autowired
    private AzureConnectRepository azureConnectRepository;

    @Override
    public Page<AzureConnect> getAll(Pageable pageable) {
        return azureConnectRepository.findAll(pageable);
    }

    @Override
    public AzureConnect getById(Long id) {
        return azureConnectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "AzureConnect not found wit Id" + id));
    }

    @Override
    public AzureConnect create(AzureConnect azureConnect) {
        return azureConnectRepository.save(azureConnect);
    }

    @Override
    public AzureConnect update(Long id, AzureConnect azureConnectRequest) {
        AzureConnect azureConnect = azureConnectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AzureConnect", "Id", id));

        azureConnect.setId(azureConnectRequest.getId())
                .setStatus(azureConnectRequest.getStatus());

        return azureConnectRepository.save(azureConnect);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        AzureConnect azureConnect = azureConnectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AzureConnect", "Id", id));

        azureConnectRepository.delete(azureConnect);
        return ResponseEntity.ok().build();
    }
}
