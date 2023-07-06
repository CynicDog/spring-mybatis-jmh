package com.example.web.vo;

import java.util.Date;

public class User {

    private String username;
    private String email;
    private String password;
    private String fullName;
    private Date createdDated;
    private Date updatedDated;

    public User() { }

    public User(String username, String email, String password, String fullName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.createdDated = new Date();
        this.updatedDated = new Date();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getCreatedDated() {
        return createdDated;
    }

    public void setCreatedDated(Date createdDated) {
        this.createdDated = createdDated;
    }

    public Date getUpdatedDated() {
        return updatedDated;
    }

    public void setUpdatedDated(Date updatedDated) {
        this.updatedDated = updatedDated;
    }
}
