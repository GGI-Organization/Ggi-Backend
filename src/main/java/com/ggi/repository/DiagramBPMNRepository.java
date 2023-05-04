package com.ggi.repository;

import com.ggi.model.DiagramBPMN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagramBPMNRepository extends JpaRepository<DiagramBPMN, Long> {
}
