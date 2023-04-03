package com.ggi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@MappedSuperclass
public abstract class User extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String password;

    @NotNull
    private String loginStatus;

    @NotNull
    private String userName;

    @NotNull
    private String email;

    @NotNull
    private Date registerDate;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public User setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public User setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
        return this;
    }
}
