package com.ggi.service;

import com.ggi.domain.model.Item;
import com.ggi.domain.repository.FormRepository;
import com.ggi.domain.repository.ItemRepository;
import com.ggi.domain.service.ItemService;
import com.ggi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FormRepository formRepository;

    @Override
    public Page<Item> getAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    @Override
    public Page<Item> getAllItemsByFormId(Long formId, Pageable pageable) {
        return itemRepository.findByFormId(formId, pageable);
    }

    @Override
    public Item getItemByIdAndFormId(Long id, Long formId) {
        return itemRepository.findByIdAndFormId(id, formId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Item not found with Id " + id +
                                " and FormId " + formId));
    }

    @Override
    public Item getById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Item not found wit Id" + id));
    }

    @Override
    public Item create(Long formId, Item item) {
        return formRepository.findById(formId).map(form -> {
            item.setForm(form);
            return itemRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Form", "Id", formId));
    }

    @Override
    public Item update(Long id, Long formId, Item itemDetails) {
        if (!formRepository.existsById(formId))
            throw new ResourceNotFoundException("Form", "Id", formId);

        return itemRepository.findById(id).map(item -> {
            item.setType(itemDetails.getType());
            return itemRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Item", "Id", id));
    }

    @Override
    public ResponseEntity<?> delete(Long id, Long formId) {
        if(!formRepository.existsById(formId))
            throw new ResourceNotFoundException("Form", "Id", formId);

        return itemRepository.findById(id).map(item -> {
            itemRepository.delete(item);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Item", "Id", id));
    }
}
