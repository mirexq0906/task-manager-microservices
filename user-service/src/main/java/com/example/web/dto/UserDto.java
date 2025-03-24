package com.example.web.dto;

import com.example.entity.Role;
import lombok.Data;

@Data
public class UserDto {

    private String username;

    private String email;

    private String password;

    private Role role;

}
