package com.ggi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name="fk_diagram")
    private DiagramBPMN diagramBPMN;

    public Task() {
    }

    public DiagramBPMN getDiagramBPMN() {
        return diagramBPMN;
    }

    public void setDiagramBPMN(DiagramBPMN diagramBPMN) {
        this.diagramBPMN = diagramBPMN;
    }

    public Task(String name, DiagramBPMN diagramBPMN){
        this.diagramBPMN = diagramBPMN;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}