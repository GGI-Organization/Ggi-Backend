package com.ggi.resource;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class SaveClientResource {

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
    private String accountSettings;

    public String getPassword() {
        return password;
    }

    public SaveClientResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public SaveClientResource setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public SaveClientResource setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SaveClientResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public SaveClientResource setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
        return this;
    }

    public String getAccountSettings() {
        return accountSettings;
    }

    public SaveClientResource setAccountSettings(String accountSettings) {
        this.accountSettings = accountSettings;
        return this;
    }
}
