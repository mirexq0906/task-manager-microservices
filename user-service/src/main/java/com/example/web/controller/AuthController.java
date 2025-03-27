package com.example.web.controller;

import com.example.entity.User;
import com.example.service.AuthService;
import com.example.web.dto.UserDto;
import com.example.web.dto.validate.OnCreate;
import com.example.web.mapper.UserMapper;
import com.example.web.response.JwtResponse;
import com.example.web.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.authService.login(userDto)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Validated(OnCreate.class) UserDto userDto) {
        User user = this.authService.register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.userMapper.userToUserResponse(user)
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<Void> refresh(@RequestHeader("Authorization") String token) {
        System.out.println(token);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
