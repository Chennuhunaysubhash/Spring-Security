package com.example.rolebasedsecurity.service;

import com.example.rolebasedsecurity.dto.UserDTO;
import com.example.rolebasedsecurity.entity.Role;
import com.example.rolebasedsecurity.entity.User;
import com.example.rolebasedsecurity.repository.RoleRepository;
import com.example.rolebasedsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultUserServiceImpl implements DefaultUserService{

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    @Override
    public User save(UserDTO userRegisteredDTO) {
        Role role = new Role();
        if(userRegisteredDTO.getRole().equals("USER"))
            role = roleRepo.findByRole("ROLE_USER");
        else if(userRegisteredDTO.getRole().equals("ADMIN"))
            role = roleRepo.findByRole("ROLE_ADMIN");
        User user = new User();
        user.setEmail(userRegisteredDTO.getEmail());
        user.setUserName(userRegisteredDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userRegisteredDTO.getPassword()));
        user.setRole(role);

        return userRepo.save(user);
    }
}
