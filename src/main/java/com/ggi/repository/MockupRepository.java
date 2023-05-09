package com.ggi.repository;

import com.ggi.model.Mockup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MockupRepository extends JpaRepository<Mockup, Long> {
}
