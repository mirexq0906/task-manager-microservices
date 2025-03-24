package com.example.repository.mapper;

import com.example.entity.Role;
import com.example.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRowMapper {

    public static User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setRole(Role.valueOf(resultSet.getString("role")));

        Timestamp createTime = resultSet.getTimestamp("create_time");
        Timestamp updateTime = resultSet.getTimestamp("update_time");
        user.setCreateTime(createTime.toLocalDateTime());
        user.setUpdateTime(updateTime.toLocalDateTime());

        return user;
    }

}
