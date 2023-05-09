package com.ggi.repository;

import com.ggi.model.MockupGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MockupGroupRepository extends JpaRepository<MockupGroup, Long> {
}
