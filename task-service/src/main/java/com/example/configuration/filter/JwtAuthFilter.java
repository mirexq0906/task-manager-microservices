package com.example.configuration.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String bearerToken = request.getHeader(HEADER_NAME);

        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            bearerToken = bearerToken.substring(BEARER_PREFIX.length());
        }

//        if (bearerToken != null && jwtService.isValidToken(bearerToken)) {
//            SecurityContextHolder.getContext().setAuthentication(jwtService.getAuthentication(bearerToken));
//        }

        filterChain.doFilter(request, response);

    }

}
