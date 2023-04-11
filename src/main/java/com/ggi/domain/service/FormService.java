package com.ggi.domain.service;

import com.ggi.domain.model.Form;
import com.ggi.domain.model.FormManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FormService {
    Page<Form> getAll(Pageable pageable);
    Page<Form> getAllFormsByFormManagerId(Long formManagerId, Pageable pageable);
    Form getFormByIdAndFormManagerId(Long id, Long formManagerId);
    Form getById(Long id);
    Form create(Long formManagerId, FormManager formManager);
    Form update(Long id, Long formManagerId, FormManager formManagerDetails);
    ResponseEntity<?> delete(Long id, Long formManagerId);
}
