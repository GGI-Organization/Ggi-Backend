package com.ggi.resource.response;

public class ComponentRes {
    public String type;
    public int posX;
    public int posY;
    public int width;
    public int height;
    public ComponentRes(String type, int posX, int posY, int width, int height){
        this.type = type;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }
}
