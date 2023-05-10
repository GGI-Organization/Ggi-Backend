package com.ggi.payload.response;

import java.util.ArrayList;

public class MockupRes {
    private ArrayList<ComponentRes> components;
    private TaskDetailRes task;
    public MockupRes(){}
    public MockupRes(ArrayList<ComponentRes> components, TaskDetailRes task){
        this.components = components;
        this.task = task;
    }

    public ArrayList<ComponentRes> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<ComponentRes> components) {
        this.components = components;
    }

    public TaskDetailRes getTask() {
        return task;
    }

    public void setTask(TaskDetailRes task) {
        this.task = task;
    }
}
