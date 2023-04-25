package com.ggi.service;

import com.ggi.domain.model.Form;
import com.ggi.domain.model.FormManager;
import com.ggi.domain.repository.FormManagerRepository;
import com.ggi.domain.repository.FormRepository;
import com.ggi.domain.service.FormManagerService;
import com.ggi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FormManagerServiceImpl implements FormManagerService {

    @Autowired
    private FormManagerRepository formManagerRepository;

    @Autowired
    private FormRepository formRepository;


    @Override
    public Page<FormManager> getAll(Pageable pageable) {
        return formManagerRepository.findAll(pageable);
    }

    @Override
    public FormManager getById(Long id) {
        return formManagerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "FormManager not found wit Id" + id));
    }

    @Override
    public FormManager create(FormManager formManager) {
        return formManagerRepository.save(formManager);
    }

    @Override
    public FormManager update(Long id, FormManager formManagerRequest) {
        FormManager formManager = formManagerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FormManager", "Id", id));

        formManager.setId(formManagerRequest.getId())
                .setStatus(formManagerRequest.getStatus());

        return formManagerRepository.save(formManager);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        FormManager formManager = formManagerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FormManager", "Id", id));

        formManagerRepository.delete(formManager);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<FormManager> getAllByStatus(boolean status) {
        return null;
    }

    @Override
    public FormManager assignFormById(Long id, Long formId) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form", "Id", formId));
        return formManagerRepository.findById(id).map(
                        formManager -> formManagerRepository.save(formManager.addForm(form)))
                .orElseThrow(() -> new ResourceNotFoundException("FormManager", "Id", id));
    }

    @Override
    public FormManager UnAssignFormById(Long id, Long formId) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form", "Id", formId));
        return formManagerRepository.findById(id).map(
                        formManager -> formManagerRepository.save(formManager.removeForm(form)))
                .orElseThrow(() -> new ResourceNotFoundException("FormManager", "Id", id));
    }
}
