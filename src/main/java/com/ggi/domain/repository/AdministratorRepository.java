package com.ggi.domain.repository;

import com.ggi.domain.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    public Optional<Administrator> findById(Long administratorId);
}
