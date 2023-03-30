package com.ggi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "diagrams")
public class Diagram extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String status;

    public Long getId() {
        return id;
    }

    public Diagram setId(Long id) {
        this.id = id;
        return this;
    }

    public String status() {
        return status;
    }

    public Diagram setStatus(String status) {
        this.status = status;
        return this;
    }
}
