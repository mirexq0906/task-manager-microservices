package com.example.service;

import com.example.entity.User;

public interface UserService {

    User findById(Long id);

    User update(User user);

    User register(User user);

}
