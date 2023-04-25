package com.ggi.resource;

import com.ggi.domain.model.AuditModel;

public class FormResource extends AuditModel {
    private Long id;
    private String type;

    public Long getId() {
        return id;
    }

    public FormResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public FormResource setType(String type) {
        this.type = type;
        return this;
    }
}
