package com.example.rolebasedsecurity.repository;

import com.example.rolebasedsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRole(String role);
}
