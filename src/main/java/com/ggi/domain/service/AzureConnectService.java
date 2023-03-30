package com.ggi.domain.service;

import com.ggi.domain.model.AzureConnect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AzureConnectService {
    Page<AzureConnect> getAll(Pageable pageable);
    AzureConnect getById(Long id);
    AzureConnect create(AzureConnect azureConnect);
    AzureConnect update(Long id, AzureConnect azureConnect);
    ResponseEntity<?> delete(Long id);
}
