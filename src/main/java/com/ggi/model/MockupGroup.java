package com.ggi.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "mockup_group")
public class MockupGroup extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EStatus status;

    @Nullable
    private String name;

    @NotNull
    private String path;

    @NotNull
    private Long userId;

    @OneToMany(mappedBy = "mockupGroup")
    private List<Mockup> mockups;

    public MockupGroup(){}

    public MockupGroup(Long userId, String name, String path, EStatus status, List<Mockup> mockups){
        this.userId = userId;
        this.name = name;
        this.path = path;
        this.status = status;
        this.mockups = mockups;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Mockup> getMockups() {
        return mockups;
    }

    public void setMockups(List<Mockup> mockups) {
        this.mockups = mockups;
    }
}
