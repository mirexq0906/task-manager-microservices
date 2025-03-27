package com.example.web.dto;

import com.example.entity.Role;
import com.example.web.dto.validate.OnCreate;
import com.example.web.dto.validate.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    @Size(min = 4, max = 20, message = "Имя пользователя должно содержать от 3 до 20 символов.", groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message = "Имя пользователя не должно быть пустым.", groups = {OnCreate.class})
    private String username;

    @Email(message = "Введите корректный адрес электронной почты.", groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message = "Адрес электронной почты обязателен.", groups = {OnCreate.class})
    private String email;

    @Size(min = 8, max = 30, message = "Пароль должен содержать от 8 до 30 символов.", groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message = "Пароль обязателен для заполнения.", groups = {OnCreate.class})
    private String password;

    private Role role;

}
