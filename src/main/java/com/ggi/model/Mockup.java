package com.ggi.model;

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

    @OneToMany(mappedBy = "mockup")
    private List<ComponentUI> components;

    @ManyToOne
    @JoinColumn(name="fk_mockup_group")
    private MockupGroup mockupGroup;

    public Mockup() {
    }

    public Mockup( String path, List<ComponentUI> components) {
        this.path = path;
        this.components = components;
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