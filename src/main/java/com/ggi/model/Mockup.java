package com.ggi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mockups")
public class Mockup extends AuditModel {

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "mockup_component_ui",
            joinColumns = @JoinColumn(name = "mockup_id"),
            inverseJoinColumns = @JoinColumn(name = "component_ui_id"))
    private Set<ComponentUI> components = new HashSet<>();

    public Mockup(){

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Mockup(Long userId, EStatus status, String name, String path, Set<ComponentUI> components){
        this.userId = userId;
        this.status = status;
        this.name = name;
        this.path = path;
        this.components = components;
    }
    public Set<ComponentUI> getComponents() {
        return components;
    }

    public void setComponents(Set<ComponentUI> components) {
        this.components = components;
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