package com.example.service;

import com.example.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    User findById(Long id);

    User update(User user);

    UserDetailsService getUserDetailsService();

}
