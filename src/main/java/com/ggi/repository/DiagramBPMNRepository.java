package com.ggi.repository;

import com.ggi.model.DiagramBPMN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagramBPMNRepository extends JpaRepository<DiagramBPMN, Long> {
    Optional<DiagramBPMN> findByUserIdAndPath(Long userId, String path);
}
