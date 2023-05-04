package com.ggi.payload.request;

import java.util.ArrayList;

public class DiagramReq {
    private String status;
    private String name;
    private String path;
    private Long userId;
    private ArrayList<TaskReq> tasks;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public ArrayList<TaskReq> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<TaskReq> tasks) {
        this.tasks = tasks;
    }
}
