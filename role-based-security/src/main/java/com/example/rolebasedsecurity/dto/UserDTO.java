package com.example.rolebasedsecurity.dto;

public class UserDTO {

    private String userName;
    private String password;
    private String email;
    private String role;

    public UserDTO(String password, String email, String role) {
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public UserDTO() {
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    }


