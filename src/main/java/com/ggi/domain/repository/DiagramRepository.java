package com.ggi.domain.repository;

import com.ggi.domain.model.Client;
import com.ggi.domain.model.Diagram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagramRepository extends JpaRepository<Diagram, Long> {
    public Optional<Diagram> findById(Long id);
}
