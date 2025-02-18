package com.app.ver.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        if (exception.getClass().equals(UsernameNotFoundException.class) || exception.getClass().equals(BadCredentialsException.class)) {
            response.sendRedirect("/login?loginBadCredentials=true");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        } else if (exception.getClass().equals(DisabledException.class)) {
            response.sendRedirect("/login?accountDisabled=true");
            response.setStatus(HttpStatus.LOCKED.value());
        }
    }
}