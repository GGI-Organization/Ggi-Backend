package com.ggi.payload.dto;

import com.ggi.model.DiagramBPMN;
import com.ggi.model.Task;

import java.util.ArrayList;
import java.util.Set;

public class DiagramBPMNDto {

    private DiagramBPMN diagramBPMN;
    private Set<Task> tasks;

    public DiagramBPMNDto(){}
    public DiagramBPMNDto(DiagramBPMN diagramBPMN, Set<Task> tasks){
        this.diagramBPMN = diagramBPMN;
        this.tasks = tasks;
    }

    public DiagramBPMN getDiagramBPMN() {
        return diagramBPMN;
    }

    public void setDiagramBPMN(DiagramBPMN diagramBPMN) {
        this.diagramBPMN = diagramBPMN;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
