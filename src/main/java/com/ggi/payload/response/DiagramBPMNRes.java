package com.ggi.payload.response;

import com.ggi.model.DiagramBPMN;
import com.ggi.model.EStatus;

import java.util.ArrayList;

public class DiagramBPMNRes {
    private Long id;
    private EStatus status;
    private String name;
    private String path;
    private ArrayList<TaskRes> tasks;

    public DiagramBPMNRes(){}
    public DiagramBPMNRes(Long id, EStatus status, String name, String path, ArrayList<TaskRes> tasks){
        this.id = id;
        this.status = status;
        this.name = name;
        this.path = path;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
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

    public ArrayList<TaskRes> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<TaskRes> tasks) {
        this.tasks = tasks;
    }
}
