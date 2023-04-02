package com.ggi.resource;

import com.ggi.domain.model.AuditModel;

public class ComponentResource extends AuditModel {
    private Long id;
    private String tag;
    private String description;

    public Long getId() {
        return id;
    }

    public ComponentResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public ComponentResource setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ComponentResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
