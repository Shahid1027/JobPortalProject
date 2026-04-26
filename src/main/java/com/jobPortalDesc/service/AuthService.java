package com.jobPortalDesc.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPortalDesc.dto.request.LoginRequest;
import com.jobPortalDesc.dto.request.RegisterRequest;
import com.jobPortalDesc.dto.response.AuthResponse;
import com.jobPortalDesc.entity.User;
import com.jobPortalDesc.exceptions.ResourceNotFoundException;
import com.jobPortalDesc.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public String register(RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return "User registered Successfully!!";
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not Found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        AuthResponse response = new AuthResponse();
        response.setRole(user.getRole());
        response.setToken("token coming");
        return response;
    }
}