package com.example.service.impl;

import com.example.entity.User;
import com.example.exception.EntityNotFoundException;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.web.dto.UserDto;
import com.example.web.mapper.UserMapper;
import com.example.web.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id " + id + " not found")
        );
    }

    @Override
    public User update(User user) {
        User updatedUser = this.findById(user.getId());
        updatedUser.setUsername(Optional.ofNullable(user.getUsername()).orElse(updatedUser.getUsername()));
        updatedUser.setPassword(Optional.ofNullable(user.getPassword()).orElse(updatedUser.getPassword()));
        updatedUser.setEmail(Optional.ofNullable(user.getEmail()).orElse(updatedUser.getEmail()));
        return this.userRepository.update(updatedUser);
    }

    @Override
    public User register(User user) {
        return this.userRepository.create(user);
    }

}
