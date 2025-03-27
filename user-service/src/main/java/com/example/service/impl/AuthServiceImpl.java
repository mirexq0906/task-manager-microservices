package com.example.service.impl;

import com.example.entity.User;
import com.example.exception.EntityNotFoundException;
import com.example.repository.UserRepository;
import com.example.service.AuthService;
import com.example.service.JwtService;
import com.example.service.UserService;
import com.example.web.dto.UserDto;
import com.example.web.mapper.UserMapper;
import com.example.web.response.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;

    private final UserService userService;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public JwtResponse login(UserDto userDto) {
        Authentication authentication = this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    userDto.getUsername(),
                    userDto.getPassword()
            )
        );
        User user = (User) authentication.getPrincipal();
        return JwtResponse.builder()
                .accessToken(jwtService.generateToken(user))
                .refreshToken(jwtService.generateRefreshToken(user))
                .build();
    }

    @Override
    public User register(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = this.userMapper.requestToUser(userDto);
        return this.userRepository.create(user);
    }

    @Override
    public JwtResponse refreshToken(String refreshToken) {
        refreshToken = refreshToken.substring(7);
        String username = this.jwtService.extractAllClaims(refreshToken).getSubject();
        User user = (User) this.userService.getUserDetailsService().loadUserByUsername(username);
        return JwtResponse.builder()
                .accessToken(this.jwtService.generateToken(user))
                .refreshToken(this.jwtService.generateRefreshToken(user))
                .build();
    }

}
