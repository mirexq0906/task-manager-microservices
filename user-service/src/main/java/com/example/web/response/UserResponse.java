package com.example.web.response;

import com.example.entity.Role;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private Role role;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
