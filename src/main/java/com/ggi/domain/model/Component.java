package com.ggi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "components")
public class Component extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String tag;

    @NotNull
    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diagram_id", nullable = true)
    private Diagram diagram;

    public Long getId() {
        return id;
    }

    public Component setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public Component setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Component setDescription(String description) {
        this.description = description;
        return this;
    }

    public Diagram getDiagram() {
        return diagram;
    }

    public Component setDiagram(Diagram diagram) {
        this.diagram = diagram;
        return this;
    }
}
