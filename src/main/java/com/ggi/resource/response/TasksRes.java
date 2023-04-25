package com.ggi.resource.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TasksRes {
    private ArrayList<TaskDetailRes> tasks;

    public TasksRes(){}
    public TasksRes(ArrayList<TaskDetailRes> tasks){
        this.tasks = tasks;
    }
    public ArrayList<TaskDetailRes> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<TaskDetailRes> tasks) {
        this.tasks = tasks;
    }
}
