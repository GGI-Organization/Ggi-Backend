package com.ggi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "component_ui")
public class ComponentUI extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // EComponent
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EComponent name;
    @NotNull
    private Long posX;
    @NotNull
    private Long posY;
    @ManyToOne
    @JoinColumn(name="fk_mockup")
    @JsonIgnore
    private Mockup mockup;
    @NotNull
    private Long width;
    @NotNull
    private Long height;
    public ComponentUI(){}
    public ComponentUI(EComponent name, Long posX, Long posY, Long width, Long height, Mockup mockup){
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.mockup = mockup;
    }

    public Mockup getMockup() {
        return mockup;
    }

    public void setMockup(Mockup mockup) {
        this.mockup = mockup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EComponent getName() {
        return name;
    }

    public void setName(EComponent name) {
        this.name = name;
    }

    public Long getPosX() {
        return posX;
    }

    public void setPosX(Long posX) {
        this.posX = posX;
    }

    public Long getPosY() {
        return posY;
    }

    public void setPosY(Long posY) {
        this.posY = posY;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }
}