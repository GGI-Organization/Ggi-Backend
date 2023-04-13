package com.ggi.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "buttons")
public class Button extends AuditModel {

    @Lob
    @NotNull
    private String description;

    public Button() {
    }

    public Button(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Button setDescription(String description) {
        this.description = description;
        return this;
    }
}
