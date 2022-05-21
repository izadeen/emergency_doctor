package com.example.emergency_doctor.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getServletPath().equals("/login")) {
            filterChain.doFilter(request, response);
        } else {

            String authorizationHeader = null;
            if(request.getHeader("Authorization")!=null){
                authorizationHeader = request.getHeader("Authorization");
            }
            else if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals("token")) {
                        authorizationHeader = cookie.getValue();
                    }
                }
            }
            if (authorizationHeader != null) {

                try {

                    UsernamePasswordAuthenticationToken authenticationToken =
                            Jwt.jwtIsValid(authorizationHeader);
                    // save the auth user
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    System.out.println(authenticationToken.isAuthenticated());
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    Map<String, String> error = new HashMap<>();
                    error.put("error", e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}

