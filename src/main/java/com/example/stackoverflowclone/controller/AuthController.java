package com.example.stackoverflowclone.controller;

import com.example.stackoverflowclone.dto.AuthRequest;
import com.example.stackoverflowclone.dto.AuthResponse;
import com.example.stackoverflowclone.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/auth")
    public AuthResponse creatAuthToken(@RequestBody AuthRequest authRequest, HttpServletResponse response) throws IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect credentials");
        } catch (DisabledException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return new AuthResponse(jwt);
    }
}
