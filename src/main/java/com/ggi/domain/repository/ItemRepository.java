package com.ggi.domain.repository;

import com.ggi.domain.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findByFormId(Long id, Pageable pageable);
    Optional<Item> findByIdAndFormId(Long id, Long formId);
}
