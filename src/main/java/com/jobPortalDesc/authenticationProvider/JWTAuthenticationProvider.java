package com.jobPortalDesc.authenticationProvider;

import com.jobPortalDesc.token.JWTAuthenticationToken;
import com.jobPortalDesc.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JWTAuthenticationProvider(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = ((JWTAuthenticationToken) authentication).getToken();
        String email = jwtUtil.validateAndExtractUsername(token);

        if (email == null) {
            throw new BadCredentialsException("Invalid JWT Token");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthenticationToken.class.isAssignableFrom(authentication);
    }
}