package com.ggi.resource;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class SaveAdministratorResource {

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

    @NotNull
    private String authorityLevel;

    @NotNull
    private String[] shareRegister;

    public String getPassword() {
        return password;
    }

    public SaveAdministratorResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public SaveAdministratorResource setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public SaveAdministratorResource setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SaveAdministratorResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public SaveAdministratorResource setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
        return this;
    }

    public String getAuthorityLevel() {
        return authorityLevel;
    }

    public SaveAdministratorResource setAuthorityLevel(String authorityLevel) {
        this.authorityLevel = authorityLevel;
        return this;
    }

    public String[] getShareRegister() {
        return shareRegister;
    }

    public SaveAdministratorResource setShareRegister(String[] shareRegister) {
        this.shareRegister = shareRegister;
        return this;
    }
}
