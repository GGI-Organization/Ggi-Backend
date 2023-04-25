package com.ggi.domain.repository;

import com.ggi.domain.model.FormManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormManagerRepository extends JpaRepository<FormManager, Long> {
}
