package com.ggi.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "texts")
public class Text extends AuditModel {

    @Lob
    @NotNull
    private String description;

    public Text() {
    }

    public Text(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Text setDescription(String description) {
        this.description = description;
        return this;
    }
}
