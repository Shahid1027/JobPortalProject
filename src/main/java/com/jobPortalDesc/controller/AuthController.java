package com.jobPortalDesc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPortalDesc.dto.request.LoginRequest;
import com.jobPortalDesc.dto.request.RegisterRequest;
import com.jobPortalDesc.dto.response.AuthResponse;
import com.jobPortalDesc.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest request){
		String response = authService.register(request);
		return ResponseEntity.ok(response);
		
	}
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
		AuthResponse response = authService.login(request);
		return ResponseEntity.ok(response);
	}

}
