package com.ggi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "diagrams")
public class Diagram extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "diagram",
            cascade = CascadeType.ALL)
    private List<Component> components;

    public Long getId() {
        return id;
    }

    public Diagram setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Diagram setStatus(String status) {
        this.status = status;
        return this;
    }

    public List<Component> getComponents() {
        return components;
    }

    public Diagram setComponents(List<Component> components) {
        this.components = components;
        return this;
    }

    private boolean hasTheComponentOf(Component component) {
        return this.getComponents().contains(component);
    }

    public Diagram addComponent(Component component) {
        if(!this.hasTheComponentOf(component)) {
            this.getComponents().add(component);
        }
        return this;
    }

    public Diagram removeComponent(Component component) {
        if(this.hasTheComponentOf(component))
            this.getComponents().remove(component);
        return this;
    }
}
