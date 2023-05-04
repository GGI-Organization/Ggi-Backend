package com.ggi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "diagrams_bpmn")
public class DiagramBPMN extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // EStatus
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EStatus status;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    private String path;

    public DiagramBPMN(){}
    public DiagramBPMN(Long userId, EStatus status, String name, String path, Set<Task> tasks){
        this.userId = userId;
        this.status = status;
        this.name = name;
        this.path = path;
        this.tasks = tasks;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "diagram_bpmn_task",
            joinColumns = @JoinColumn(name = "diagram_bpmn_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))



    private Set<Task> tasks = new HashSet<>();

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
