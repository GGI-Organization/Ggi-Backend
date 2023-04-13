package com.ggi.service;

import com.ggi.domain.model.Form;
import com.ggi.domain.model.Item;
import com.ggi.domain.repository.FormManagerRepository;
import com.ggi.domain.repository.FormRepository;
import com.ggi.domain.repository.ItemRepository;
import com.ggi.domain.service.FormService;
import com.ggi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private FormManagerRepository formManagerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Page<Form> getAll(Pageable pageable) {
        return formRepository.findAll(pageable);
    }

    @Override
    public Page<Form> getAllFormsByFormManagerId(Long formManagerId, Pageable pageable) {
        return formRepository.findByFormManagerId(formManagerId, pageable);
    }

    @Override
    public Form getFormByIdAndFormManagerId(Long id, Long formManagerId) {
        return formRepository.findByIdAndFormManagerId(id, formManagerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Form not found with Id" + id +
                                " and FormManagerId " + formManagerId));
    }

    @Override
    public Form getById(Long id) {
        return formRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Form not found wit Id" + id));
    }

    @Override
    public Form create(Long formManagerId, Form form) {
        return formManagerRepository.findById(formManagerId).map(formManager -> {
            form.setFormManager(formManager);
            return formRepository.save(form);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "FormManager", "Id", formManagerId));
    }

    @Override
    public Form update(Long id, Long formManagerId, Form formDetails) {
        if (!formManagerRepository.existsById(formManagerId))
            throw new ResourceNotFoundException("FormManager", "Id", formManagerId);

        return formRepository.findById(id).map(form -> {
            form.setType(formDetails.getType());
            return formRepository.save(formDetails);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Form", "Id", id));
    }

    @Override
    public ResponseEntity<?> delete(Long id, Long formManagerId) {
        if(!formManagerRepository.existsById(formManagerId))
            throw new ResourceNotFoundException("FormManager", "Id", formManagerId);

        return formRepository.findById(id).map(form -> {
            formRepository.delete(form);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Form", "Id", id));
    }

    @Override
    public Form assignItemById(Long id, Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "Id", itemId));
        return formRepository.findById(id).map(form ->
                        formRepository.save(form.addItem(item)))
                .orElseThrow(() -> new ResourceNotFoundException("Form", "Id", id));
    }

    @Override
    public Form UnAssignItemById(Long id, Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "Id", itemId));
        return formRepository.findById(id).map(form ->
                        formRepository.save(form.removeItem(item)))
                .orElseThrow(() -> new ResourceNotFoundException("Form", "Id", id));
    }
}
