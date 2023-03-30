package com.ggi.service;

import com.ggi.domain.model.AzureConnect;
import com.ggi.domain.repository.AzureConnectRepository;
import com.ggi.domain.service.AzureConnectService;
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
        return null;
    }

    @Override
    public AzureConnect getById(Long id) {
        return null;
    }

    @Override
    public AzureConnect create(AzureConnect azureConnect) {
        return null;
    }

    @Override
    public AzureConnect update(Long id, AzureConnect azureConnect) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }
}
