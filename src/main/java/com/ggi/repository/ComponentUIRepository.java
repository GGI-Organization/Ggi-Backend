package com.ggi.repository;

import com.ggi.model.ComponentUI;
import com.ggi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComponentUIRepository extends JpaRepository<ComponentUI, Long> {
}
