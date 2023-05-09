package com.ggi.payload.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDetailRes {
    private String task;
    private String type;
    private String keyWord;
    public  TaskDetailRes(){}
    public TaskDetailRes(String task, String type, String keyWord){
        this.task = task;
        this.type = type;
        this.keyWord = keyWord;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
