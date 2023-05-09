package com.ggi.payload.response;

import java.util.List;

public class LoginRes {
    private String token;
    private Long id;
    private String fullname;
    private String email;
    private List<String> roles;

    public LoginRes(Long id, String token, String fullname, String email, List<String> roles){
        this.id = id;
        this.token = token;
        this.fullname = fullname;
        this.email = email;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String username) {
        this.fullname = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
