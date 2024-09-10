package com.example.rolebasedsecurity.entity;

import jakarta.persistence.*;

@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String role;



    public Role() {
        super();
    }

    public Role(String role) {
        super();
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
