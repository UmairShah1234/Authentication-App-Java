package com.devlanzer.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devlanzer.authentication.model.LoginDTO;
import com.devlanzer.authentication.model.RegisterDTO;
import com.devlanzer.authentication.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginDTO loginDTO) {
        return authenticationService.authenticate(loginDTO);
    }

    @PostMapping("/register")
    public String createUser(@RequestBody RegisterDTO registerDTO, HttpServletRequest request) {
        return authenticationService.createUser(registerDTO, request);
    }
}
