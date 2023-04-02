package com.ggi.resource;

import com.ggi.domain.model.AuditModel;

import java.util.Date;

public class AdministratorResource extends AuditModel {
    private Long id;
    private String password;
    private String loginStatus;
    private String userName;
    private String email;
    private Date registerDate;
    private String authorityLevel;
    private String[] shareRegister;

    public Long getId() {
        return id;
    }

    public AdministratorResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AdministratorResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public AdministratorResource setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public AdministratorResource setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AdministratorResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public AdministratorResource setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
        return this;
    }

    public String getAuthorityLevel() {
        return authorityLevel;
    }

    public AdministratorResource setAuthorityLevel(String authorityLevel) {
        this.authorityLevel = authorityLevel;
        return this;
    }

    public String[] getShareRegister() {
        return shareRegister;
    }

    public AdministratorResource setShareRegister(String[] shareRegister) {
        this.shareRegister = shareRegister;
        return this;
    }
}
