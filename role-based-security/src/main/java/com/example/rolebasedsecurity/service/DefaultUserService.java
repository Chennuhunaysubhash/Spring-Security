package com.example.rolebasedsecurity.service;

import com.example.rolebasedsecurity.dto.UserDTO;
import com.example.rolebasedsecurity.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface DefaultUserService extends UserDetailsService {
    User save(UserDTO userRegisteredDTO);

}