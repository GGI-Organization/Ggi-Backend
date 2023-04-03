package com.ggi.domain.repository;

import com.ggi.domain.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
