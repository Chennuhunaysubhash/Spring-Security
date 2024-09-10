package com.example.springsecurity.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.springsecurity.config.JwtGeneratorValidator;
import com.example.springsecurity.dto.UserDTO;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RestAppController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtGeneratorValidator jwtGenVal;

    @Autowired
    BCryptPasswordEncoder bcCryptPasswordEncoder;

    @PostMapping("/registration")
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(bcCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setUserName(userDto.getUserName());
        User users = userRepo.save(user);
        if (users.equals(null))
            return generateRespose("Not able to save user ", HttpStatus.BAD_REQUEST, user);
        else
            return generateRespose("User saved successfully : " + users.getId(), HttpStatus.OK, users);
    }

    @GetMapping("/genToken")
    public String generateJwtToken(@RequestBody UserDTO userDto) throws Exception {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtGenVal.generateToken(userDto.getUserName());
    }


    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome";
    }



    public ResponseEntity<Object> generateRespose(String message, HttpStatus st, Object responseobj) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("meaasge", message);
        map.put("Status", st.value());
        map.put("data", responseobj);

        return new ResponseEntity<Object>(map, st);
    }

}
