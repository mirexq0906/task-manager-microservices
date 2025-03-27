package com.example.service.impl;

import com.example.entity.User;
import com.example.service.JwtService;
import com.example.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final UserService userService;

    @Value("${app.jwt.refresh}")
    private Duration refresh;

    @Value("${app.jwt.access}")
    private Duration access;

    @Value("${app.jwt.secret}")
    private String secretKey;

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + this.access.toMillis()))
                .signWith(getSecretKey())
                .compact();
    }

    @Override
    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + this.refresh.toMillis()))
                .signWith(getSecretKey())
                .compact();
    }

    @Override
    public boolean isValidToken(String token) {
        return !this.extractAllClaims(token).getExpiration().before(new Date());
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public Authentication getAuthentication(String token) {
        String username = this.extractAllClaims(token).getSubject();
        User user = (User) this.userService.getUserDetailsService().loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()

        );
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secretKey));
    }

}
