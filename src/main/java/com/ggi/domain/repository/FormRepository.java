package com.ggi.domain.repository;

import com.ggi.domain.model.Form;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    Page<Form> findByFormManagerId(Long id, Pageable pageable);
    Optional<Form> findByIdAndFormManagerId(Long id, Long formManagerId);
}
