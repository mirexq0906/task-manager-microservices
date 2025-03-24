package com.example.repository.impl;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.repository.mapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String FIND_BY_ID = """
            SELECT id, username, email, password, role, create_time, update_time
            FROM users
            WHERE id = ?
            """;

    private final String CREATE = """
            INSERT INTO users (username, email, password, role)
            VALUES (?, ?, ?, ?)
            RETURNING id, username, email, password, role, create_time, update_time;
            """;

    private final String UPDATE = """
            UPDATE users SET username = ?, email = ?, password = ?, role = ?
            WHERE id = ?
            RETURNING id, username, email, password, role, create_time, update_time;
            """;

    @Override
    public Optional<User> findById(Long id) {
        User user = this.jdbcTemplate.queryForObject(FIND_BY_ID, UserRowMapper::mapRow, id);
        return Optional.ofNullable(user);
    }

    @Override
    public User create(User user) {
        return this.jdbcTemplate.queryForObject(
                CREATE,
                UserRowMapper::mapRow,
                user.getUsername(), user.getEmail(), user.getPassword(),
                user.getRole().toString()
        );
    }

    @Override
    public User update(User user) {
        return this.jdbcTemplate.queryForObject(
                UPDATE,
                UserRowMapper::mapRow,
                user.getUsername(), user.getEmail(), user.getPassword(),
                user.getRole().toString(), user.getId()
        );
    }

}
