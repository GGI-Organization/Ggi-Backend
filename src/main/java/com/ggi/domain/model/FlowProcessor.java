package com.ggi.domain.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "processors")
public class FlowProcessor extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String status;

    public Long getId() {
        return id;
    }

    public FlowProcessor setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public FlowProcessor setStatus(String status) {
        this.status = status;
        return this;
    }
}
