package com.ggi.domain.service;

import com.ggi.domain.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ItemService {
    Page<Item> getAll(Pageable pageable);
    Page<Item> getAllItemsByFormId(Long formId, Pageable pageable);
    Item getItemByIdAndFormId(Long id, Long formId);
    Item getById(Long id);
    Item create(Long formId, Item item );
    Item update(Long id, Long formId, Item itemDetails);
    ResponseEntity<?> delete(Long id, Long formId);
}
