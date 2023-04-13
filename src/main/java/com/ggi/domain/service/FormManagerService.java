package com.ggi.domain.service;

import com.ggi.domain.model.FormManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FormManagerService {
    Page<FormManager> getAll(Pageable pageable);
    FormManager getById(Long id);
    FormManager create(FormManager formManager);
    FormManager update(Long id, FormManager formManagerRequest);
    ResponseEntity<?> delete(Long id);
    Page<FormManager> getAllByStatus(boolean status);

    FormManager assignFormById(Long id, Long formId);
    FormManager UnAssignFormById(Long id, Long formId);
}
