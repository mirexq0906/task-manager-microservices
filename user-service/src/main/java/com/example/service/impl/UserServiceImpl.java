package com.example.service.impl;

import com.example.entity.User;
import com.example.exception.EntityNotFoundException;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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
        updatedUser.setEmail(Optional.ofNullable(user.getEmail()).orElse(updatedUser.getEmail()));
        return this.userRepository.update(updatedUser);
    }

    @Override
    public UserDetailsService getUserDetailsService() {
        return (username) -> this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User with username " + username + " not found"));
    }

}
