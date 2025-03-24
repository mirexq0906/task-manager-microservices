package com.example.web.mapper;

import com.example.entity.User;
import com.example.web.dto.UserDto;
import com.example.web.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    public User requestToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;
    }

    public User requestToUser(UserDto userDto, Long userId) {
        User user = this.requestToUser(userDto);
        user.setId(userId);
        return user;
    }

    public UserResponse userToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole());
        userResponse.setCreateTime(user.getCreateTime());
        userResponse.setUpdateTime(user.getUpdateTime());
        return userResponse;
    }

}
