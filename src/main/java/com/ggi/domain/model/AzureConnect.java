package com.ggi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "connections")
public class AzureConnect extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String status;

    public AzureConnect() {
    }

    public AzureConnect(@NotNull String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public AzureConnect setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public AzureConnect setStatus(String status) {
        this.status = status;
        return this;
    }
}
