package com.ggi.domain.repository;

import com.ggi.domain.model.FlowProcessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowProcessorRepository extends JpaRepository<FlowProcessor, Long> {
}
