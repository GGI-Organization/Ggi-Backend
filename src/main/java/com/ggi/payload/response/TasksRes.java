package com.ggi.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TasksRes {
    private ArrayList<TaskDetailRes> tasks;
    private String path;

    public TasksRes() {
    }

    public TasksRes(ArrayList<TaskDetailRes> tasks) {
        this.tasks = tasks;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<TaskDetailRes> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<TaskDetailRes> tasks) {
        this.tasks = tasks;
    }
}
