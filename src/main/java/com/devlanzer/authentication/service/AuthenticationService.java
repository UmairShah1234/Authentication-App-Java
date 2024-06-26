package com.devlanzer.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devlanzer.authentication.events.RegistrationEvent;
import com.devlanzer.authentication.model.LoginDTO;
import com.devlanzer.authentication.model.RegisterDTO;
import com.devlanzer.authentication.model.Role;
import com.devlanzer.authentication.model.User;
import com.devlanzer.authentication.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ApplicationEventPublisher applicationEventPublisher;

    public String authenticate(LoginDTO loginDTO) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow();

        String jwt = jwtService.generateToken(user.getEmail());

        return jwt;

    }

    public String createUser(RegisterDTO registerDTO, HttpServletRequest request) {

        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setRole(Role.USER);

        userRepository.save(user);

        applicationEventPublisher.publishEvent(new RegistrationEvent(user, createApplcationUrl(request)));

        return userRepository.findByEmail(user.getEmail()).toString();
    }

    private String createApplcationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}
