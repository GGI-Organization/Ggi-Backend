package com.ggi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "forms")
public class Form extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_manager_id", nullable = true)
    private FormManager formManager;

    public Form() {
    }

    public Form(String type, FormManager formManager) {
        this.type = type;
        this.formManager = formManager;
    }

    public Long getId() {
        return id;
    }

    public Form setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Form setType(String type) {
        this.type = type;
        return this;
    }

    public FormManager getFormManager() {
        return formManager;
    }

    public Form setFormManager(FormManager formManager) {
        this.formManager = formManager;
        return this;
    }
}
