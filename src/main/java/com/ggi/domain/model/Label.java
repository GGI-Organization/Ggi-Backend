package com.ggi.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "labels")
public class Label extends Item {

    @Lob
    @NotNull
    private String description;

    public Label() {
    }

    public Label(@NotNull String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Label setDescription(String description) {
        this.description = description;
        return this;
    }
}
