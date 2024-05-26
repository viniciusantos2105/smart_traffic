package com.smart_traffic.config.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart_traffic.exceptions.commons.TokenException;
import com.smart_traffic.models.UserModel;
import com.smart_traffic.repositories.user.UserRepositoryImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recoverToken(request);
        if (token != null) {
            try {
                var subject = tokenService.validarToken(token);
                UserModel user = userRepositoryImpl.findClientByEmail(subject).get();
                var autenticar = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autenticar);
            } catch (TokenException e) {
                populateError(response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private HttpServletResponse populateError(HttpServletResponse response, TokenException e) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", e.getMessage());
        errorDetails.put("status", TokenException.DEFAULT_STATUS);
        response.getWriter().write(mapper.writeValueAsString(errorDetails));
        return response;
    }

private String recoverToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");
    if (authHeader == null) return null;
    return authHeader.replace("Bearer ", "");
}
}