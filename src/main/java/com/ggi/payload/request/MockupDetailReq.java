package com.ggi.payload.request;

import java.util.ArrayList;

public class MockupDetailReq {
    private String status;
    private String name;
    private String path;
    private Long userId;

    private ArrayList<ComponentReq> components;

    public ArrayList<ComponentReq> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<ComponentReq> components) {
        this.components = components;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
