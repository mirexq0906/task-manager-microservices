package com.example.repository;

import com.example.entity.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    User create(User user);

    User update(User user);

}
