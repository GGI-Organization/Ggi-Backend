package com.ggi.model;

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
    @NotNull
    private Long width;
    @NotNull
    private Long height;
    public ComponentUI(){}
    public ComponentUI(EComponent name, Long posX, Long posY, Long width, Long height){
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
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