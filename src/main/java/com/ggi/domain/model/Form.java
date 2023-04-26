package com.ggi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "form",
            cascade = CascadeType.ALL)
    private List<Item> items;

    public Form() {
    }

    public Form(@NotNull String type) {
        this.type = type;
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

    public List<Item> getItems() {
        return items;
    }

    public Form setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    private boolean hasTheItemOf(Item item) {
        return this.getItems().contains(item);
    }

    public Form addItem(Item item) {
        if(!this.hasTheItemOf(item)) {
            this.getItems().add(item);
        }
        return this;
    }

    public Form removeItem(Item item) {
        if(this.hasTheItemOf(item))
            this.getItems().remove(item);
        return this;
    }
}
