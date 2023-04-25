package com.ggi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "forms_manager")
public class FormManager extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "form_manager",
            cascade = CascadeType.ALL)
    private List<Form> forms;

    public FormManager() {
    }

    public FormManager(@NotNull String status) {
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public FormManager setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public FormManager setStatus(String status) {
        this.status = status;
        return this;
    }

    public List<Form> getForms() {
        return forms;
    }

    public FormManager setForms(List<Form> forms) {
        this.forms = forms;
        return this;
    }

    private boolean hasTheFormOf(Form form) {
        return this.getForms().contains(form);
    }

    public FormManager addForm(Form form) {
        if (!this.hasTheFormOf(form)) {
            this.getForms().add(form);
        }
        return this;
    }

    public FormManager removeForm(Form form) {
        if(this.hasTheFormOf(form))
            this.getForms().remove(form);
        return this;
    }
}
