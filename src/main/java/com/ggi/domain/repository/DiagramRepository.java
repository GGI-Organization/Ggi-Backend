package com.ggi.domain.repository;

import com.ggi.domain.model.Client;
import com.ggi.domain.model.Diagram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiagramRepository extends JpaRepository<Diagram, Long> {
    public Optional<Diagram> findById(Long id);
}
