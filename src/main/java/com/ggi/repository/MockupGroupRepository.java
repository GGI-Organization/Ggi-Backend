package com.ggi.repository;

import com.ggi.model.MockupGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MockupGroupRepository extends JpaRepository<MockupGroup, Long> {
    Optional<MockupGroup> findByUserIdAndPath(Long userId, String path);
}
