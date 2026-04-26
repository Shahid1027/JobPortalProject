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





//**1. Register USER:**
//```
//POST http://localhost:8081/auth/register
//{
//    "name": "Shahid",
//    "email": "shahid@abc.com",
//    "password": "123",
//    "role": "ROLE_USER"
//}
//```
//
//```
//POST http://localhost:8081/auth/register
//{
//    "name": "Ahmed",
//    "email": "ahmed@abc.com",
//    "password": "123",
//    "role": "ROLE_USER"
//}
//```
//
//```
//POST http://localhost:8081/auth/register
//{
//    "name": "Ali",
//    "email": "ali@abc.com",
//    "password": "123",
//    "role": "ROLE_USER"
//}
//```
//
//---
//
//**2. Register COMPANY:**
//```
//POST http://localhost:8081/auth/register
//{
//    "name": "Infosys",
//    "email": "infosys@abc.com",
//    "password": "123",
//    "role": "ROLE_COMPANY"
//}
//```
//
//```
//POST http://localhost:8081/auth/register
//{
//    "name": "TCS",
//    "email": "tcs@abc.com",
//    "password": "123",
//    "role": "ROLE_COMPANY"
//}
//```
//
//```
//POST http://localhost:8081/auth/register
//{
//    "name": "Wipro",
//    "email": "wipro@abc.com",
//    "password": "123",
//    "role": "ROLE_COMPANY"
//}
//```
//
//---
//
//**3. Login - Token lo:**
//```
//POST http://localhost:8081/auth/login
//{
//    "email": "infosys@abc.com",
//    "password": "123"
//}
//```
//Response Headers se token copy karo!
//
//---
//
//**4. Job Post - COMPANY token se:**
//```
//POST http://localhost:8081/jobs/post?email=infosys@abc.com
//Authorization: Bearer <token>
//{
//    "title": "Java Developer",
//    "description": "Spring Boot experience required",
//    "loaction": "Bangalore",
//    "salary": "8 LPA"
//}
//```
//
//```
//POST http://localhost:8081/jobs/post?email=infosys@abc.com
//Authorization: Bearer <token>
//{
//    "title": "Python Developer",
//    "description": "Django experience required",
//    "loaction": "Mumbai",
//    "salary": "10 LPA"
//}
//```
//
//```
//POST http://localhost:8081/jobs/post?email=tcs@abc.com
//Authorization: Bearer <token>
//{
//    "title": "React Developer",
//    "description": "ReactJS experience required",
//    "loaction": "Hyderabad",
//    "salary": "12 LPA"
//}
//```
//
//```
//POST http://localhost:8081/jobs/post?email=wipro@abc.com
//Authorization: Bearer <token>
//{
//    "title": "DevOps Engineer",
//    "description": "AWS and Docker experience required",
//    "loaction": "Pune",
//    "salary": "15 LPA"
//}
//```
//
//---
//
//**5. Apply Job - USER token se:**
//
//Pehle USER login karo token lo, phir:
//```
//POST http://localhost:8081/applications/apply?jobId=1&email=shahid@abc.com
//Authorization: Bearer <token>
//```
//
//```
//POST http://localhost:8081/applications/apply?jobId=2&email=shahid@abc.com
//Authorization: Bearer <token>
//```
//
//```
//POST http://localhost:8081/applications/apply?jobId=3&email=ahmed@abc.com
//Authorization: Bearer <token>
//```
//
//```
//POST http://localhost:8081/applications/apply?jobId=4&email=ali@abc.com
//Authorization: Bearer <token>
//```

 
