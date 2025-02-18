package com.app.ver.handler;

import com.app.ver.entity.User;
import com.app.ver.security.jwt.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import com.app.ver.entity.Role;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtils jwtUtils;

    public LoginSuccessHandler(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();

        Set<String> grantedAuthorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        Set<String> userRoles = user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());

        if (userRoles.stream().anyMatch(grantedAuthorities::contains)) {
            String jwtToken = jwtUtils.generateJwtToken(authentication);
            Cookie sessionCookie = new Cookie("Authorization", "Bearer_" + jwtToken);
            sessionCookie.setHttpOnly(true);
            sessionCookie.setPath("/");
            response.addCookie(sessionCookie);
        }
    }
}