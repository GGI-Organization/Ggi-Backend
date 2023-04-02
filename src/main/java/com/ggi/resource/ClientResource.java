package com.ggi.resource;

import com.ggi.domain.model.AuditModel;

import java.util.Date;

public class ClientResource extends AuditModel {
    private Long id;
    private String password;
    private String loginStatus;
    private String userName;
    private String email;
    private Date registerDate;
    private String accountSettings;

    public Long getId() {
        return id;
    }

    public ClientResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ClientResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public ClientResource setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public ClientResource setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ClientResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public ClientResource setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
        return this;
    }

    public String getAccountSettings() {
        return accountSettings;
    }

    public ClientResource setAccountSettings(String accountSettings) {
        this.accountSettings = accountSettings;
        return this;
    }
}
