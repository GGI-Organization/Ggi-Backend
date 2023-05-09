package com.ggi.payload.request;

import jakarta.validation.constraints.NotNull;

public class ComponentReq {
    private String name;
    private Long posX;
    private Long posY;
    private Long width;
    private Long height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
