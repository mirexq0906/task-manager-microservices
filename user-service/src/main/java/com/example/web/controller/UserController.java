package com.example.web.controller;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.web.dto.UserDto;
import com.example.web.mapper.UserMapper;
import com.example.web.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(
                this.userMapper.userToUserResponse(user)
        );
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> update(@RequestBody UserDto userDto, @PathVariable Long userId) {
        User user = this.userService.update(
                this.userMapper.requestToUser(userDto, userId)
        );
        return ResponseEntity.status(HttpStatus.OK).body(
                this.userMapper.userToUserResponse(user)
        );
    }

    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("login success");
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserDto userDto) {
        User user = this.userService.register(
                this.userMapper.requestToUser(userDto)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(
            this.userMapper.userToUserResponse(user)
        );
    }

}
