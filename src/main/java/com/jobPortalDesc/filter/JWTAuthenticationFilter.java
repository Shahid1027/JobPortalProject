package com.jobPortalDesc.filter;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jobPortalDesc.dto.request.LoginRequest;
import com.jobPortalDesc.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;


/*
 * ## JWTAuthenticationFilter ka poora flow (paragraph form):
 * 
 * Jab client se `/generate-token` endpoint par login request aati hai jisme email aur 
 * password JSON format mein hota hai, toh sabse pehle `JWTAuthenticationFilter` ka 
 * `doFilterInternal` method execute hota hai. Yeh filter check karta hai ki request ka 
 * path `/generate-token` hai ya nahi — agar nahi hai toh request ko aage bhej diya 
 * jaata hai, lekin agar hai toh aage ka process shuru hota hai.
 * 
 * Kyuki filter controller se pehle aata hai, isliye yahan Spring MVC ke annotations 
 * jaise `@RequestParam` ya `@RequestBody` kaam nahi karte. Isliye hum `ObjectMapper` 
 * ka use karte hain jo request ke input stream se JSON data padhta hai aur use 
 * `LoginRequest` object mein convert kar deta hai. Is object se hum email aur password 
 * nikal lete hain.
 * 
 * Ab hum ek `UsernamePasswordAuthenticationToken` object banate hain jisme email aur 
 * password wrap kar diya jaata hai. Ye object `AuthenticationManager` ko pass kiya 
 * jaata hai. `AuthenticationManager` internally `DaoAuthenticationProvider` ko call 
 * karta hai, jo `UserDetailsService` se user ko database se load karta hai aur 
 * `PasswordEncoder` se password match karta hai. Agar credentials sahi hote hain toh 
 * `Authentication` object return hota hai jisme `isAuthenticated()` true hota hai.
 * 
 * Authentication successful hone ke baad hum `jwtUtil` ki help se do tokens generate 
 * karte hain: ek access token jo 15 minute ke liye valid hota hai, aur doosra refresh 
 * token jo 7 din ke liye valid hota hai. Access token ko hum response ke 
 * `Authorization` header mein `Bearer` prefix ke saath bhej dete hain. Refresh token 
 * ko hum ek `HttpOnly` cookie mein set karte hain — ye cookie sirf HTTPS par bheji 
 * jaati hai, JavaScript se access nahi ki ja sakti, aur sirf `/refresh-token` endpoint 
 * ke liye available hoti hai. Is tarah client ko dono tokens mil jaate hain: access 
 * token har API call mein bhejne ke liye, aur refresh token access token expire hone 
 * par naya token lene ke liye.
 */

public class JWTAuthenticationFilter extends OncePerRequestFilter {
	
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(!request.getServletPath().equals("/auth/login")) {
			filterChain.doFilter(request, response);
			return ;
		}
		
	//Filters Controller se pehle aate hai, isliye Spring MVC ke features abhi available nahi hote, it's normal servlet hence can't use @RequestParam ==> need to convert JSON to java object.
		ObjectMapper objectMapper = new ObjectMapper();
		LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
		
		UsernamePasswordAuthenticationToken authObject = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
		Authentication authResult = authenticationManager.authenticate(authObject);
		
		if(authResult.isAuthenticated()) {
			String token = jwtUtil.generateToken(authResult.getName(), 15L);
			response.setHeader("Authorization", "Bearer" + token );
			

            String refreshToken = jwtUtil.generateToken(authResult.getName(), 7 * 24 * 60L); //7day
            // Set Refresh Token in HttpOnly Cookie
            //we can also send it in response body but then client has to store it in local storage or in-memory
            Cookie refreshCookie = new Cookie("refreshToken", refreshToken);
            refreshCookie.setHttpOnly(true); //prevent javascript from accessing it
            refreshCookie.setSecure(true); // sent only over HTTPS
            refreshCookie.setPath("/refresh-token"); // Cookie available only for refresh endpoint
            refreshCookie.setMaxAge(7 * 24 * 60 * 60); // 7 days expiry
            response.addCookie(refreshCookie);

		}
	}
	

}
