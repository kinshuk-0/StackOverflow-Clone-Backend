package com.example.stackoverflowclone.service.impl;

import com.example.stackoverflowclone.dto.SignupDTO;
import com.example.stackoverflowclone.dto.UserDTO;
import com.example.stackoverflowclone.entity.User;
import com.example.stackoverflowclone.repository.UserRepository;
import com.example.stackoverflowclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDTO createUser(SignupDTO signupDTO) {
        User user = new User();
        user.setUsername(signupDTO.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));

        User createdUser = userRepository.save(user);
        UserDTO createdUserDTO = new UserDTO();
        createdUserDTO.setId(createdUser.getUser_id());
        return  createdUserDTO;
    }

    @Override
    public boolean hasUserWithUsername(String username) {
        return userRepository.findFirstByUsername(username).isPresent();
    }
}
