package com.example.stackoverflowclone.service;

import com.example.stackoverflowclone.dto.SignupDTO;
import com.example.stackoverflowclone.dto.UserDTO;

public interface UserService {
    UserDTO createUser(SignupDTO signupDTO);

    boolean hasUserWithUsername(String username);
}
