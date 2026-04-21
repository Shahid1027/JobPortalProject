package com.jobPortalDesc.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobPortalDesc.dto.request.LoginRequest;
import com.jobPortalDesc.dto.request.RegisterRequest;
import com.jobPortalDesc.dto.response.AuthResponse;
import com.jobPortalDesc.entity.User;
import com.jobPortalDesc.exceptions.ResourceNotFoundException;
import com.jobPortalDesc.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	UserRepository userRepository;
	
	public String register(RegisterRequest request) {
		
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setRole(request.getRole());
		user.setCreatedAt(LocalDateTime.now());
		userRepository.save(user);
		
		return "User registered Successfully!!";
	}
	
	public AuthResponse login(LoginRequest request) {
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("User not Found"));
		
		if(!request.getPassword().equals(user.getPassword())) {
			throw new RuntimeException("Invalid Password");
		}
		AuthResponse response = new AuthResponse();
		response.setRole(user.getRole());
		response.setToken("token coming");
		return response;
	}

}
