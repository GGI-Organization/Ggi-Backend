package com.ggi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@MappedSuperclass
public abstract class Profile extends AuditModel {

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

    public Profile setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Profile setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public Profile setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Profile setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Profile setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public Profile setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
        return this;
    }
}
