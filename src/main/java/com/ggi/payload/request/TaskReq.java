package com.ggi.payload.request;

public class TaskReq {

    private String name;

    public TaskReq() {
    }

    public TaskReq(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
