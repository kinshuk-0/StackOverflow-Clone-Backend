package com.example.stackoverflowclone.controller;

import com.example.stackoverflowclone.dto.SignupDTO;
import com.example.stackoverflowclone.dto.UserDTO;
import com.example.stackoverflowclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody SignupDTO signupDTO) {

        if(userService.hasUserWithUsername(signupDTO.getUsername())) {
            return new ResponseEntity<>("Username already taken", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDTO createdUser = userService.createUser(signupDTO);

        if(createdUser == null) {
            return new ResponseEntity<>("Signup unsuccessful", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}
