package com.ggi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "mockup")
public class Mockup extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String path;

    @NotNull
    private String task;

    @OneToMany(mappedBy = "mockup")
    private List<ComponentUI> components;

    @ManyToOne
    @JoinColumn(name="fk_mockup_group")
    @JsonIgnore
    private MockupGroup mockupGroup;

    public Mockup() {
    }

    public Mockup( String path, String task, MockupGroup mockupGroup) {
        this.path = path;
        this.task = task;
        this.mockupGroup = mockupGroup;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public MockupGroup getMockupGroup() {
        return mockupGroup;
    }

    public void setMockupGroup(MockupGroup mockupGroup) {
        this.mockupGroup = mockupGroup;
    }

    public List<ComponentUI> getComponents() {
        return components;
    }

    public void setComponents(List<ComponentUI> components) {
        this.components = components;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}