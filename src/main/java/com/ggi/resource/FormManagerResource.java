package com.ggi.resource;

import com.ggi.domain.model.AuditModel;

public class FormManagerResource extends AuditModel {
    private Long id;
    private String status;

    public Long getId() {
        return id;
    }

    public FormManagerResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public FormManagerResource setStatus(String status) {
        this.status = status;
        return this;
    }
}
