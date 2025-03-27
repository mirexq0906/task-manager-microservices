package com.example.service;

import com.example.entity.User;
import com.example.web.dto.UserDto;
import com.example.web.response.JwtResponse;

public interface AuthService {

    JwtResponse login(UserDto userDto);

    User register(UserDto userDto);

    JwtResponse refreshToken(String refreshToken);

}
