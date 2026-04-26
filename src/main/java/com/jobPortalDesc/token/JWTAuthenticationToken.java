package com.jobPortalDesc.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import java.util.Collections;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {

    private final String token;

    public JWTAuthenticationToken(String token) {
        super(Collections.emptyList());
        this.token = token;
        setAuthenticated(false);
    }

    public String getToken() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}