package com.ggi.domain.repository;

import com.ggi.domain.model.AzureConnect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AzureConnectRepository extends JpaRepository<AzureConnect, Long> {
}
