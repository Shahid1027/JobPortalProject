package com.jobPortalDesc.util;

import java.util.Date;

import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;


@Component
public class JwtUtil {

//	@Value("${jwt.secret}")
//	private String secret;
//	
//	@Value("${jwt.expiration}")
//	private Long expiration;
//	
//	private SecretKey getSigningKey() {
//		byte[] keyBytes = Decoders.BASE64.decode(secret);
//		return Keys.hmacShaKeyFor(keyBytes);
//	}
	 private static final String SECRET_KEY = "your-secure-secret-key-min-32bytes";
	    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

	
	public String generateToken(String username, Long expiryMinutes) {
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expiryMinutes * 60 * 1000)) //miliseconds
				.signWith(key, Jwts.SIG.HS256)
				.compact();
	}
	public String validateAndExtractUsername(String token) {
		try {
			return Jwts.parser()
					.verifyWith(key)
					.build()
					.parseSignedClaims(token)
					.getPayload()
					.getSubject();
		} catch (JwtException e) {
		return null;
		}
	}
}

