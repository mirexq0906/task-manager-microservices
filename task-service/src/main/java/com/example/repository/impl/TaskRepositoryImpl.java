package com.example.repository.impl;

import com.example.model.Task;
import com.example.repository.TaskRepository;
import com.example.repository.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String FIND_ALL = """
            SELECT id, title, description, files, created_at, updated_at  
            FROM tasks;
            """;

    private final String FIND_BY_ID = """
            SELECT id, title, description, files, created_at, updated_at  
            FROM tasks
            WHERE id = ?;
            """;

    private final String CREATE = """
            INSERT INTO tasks (title, description, files)
            VALUES (?, ?, ?)
            RETURNING id, title, description, files, created_at, updated_at;
            """;

    private final String UPDATE = """
            UPDATE tasks 
            SET title = ?, description = ?, files = ?
            WHERE id = ?;
            """;

    private final String DELETE = """
            DELETE FROM tasks
            WHERE id = ?;
            """;

    @Override
    public List<Task> findAll() {
        return this.jdbcTemplate.query(FIND_ALL, TaskMapper::taskListMapper);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return this.jdbcTemplate.queryForObject(FIND_BY_ID, TaskMapper::taskMapper, id);
    }

    @Override
    public Task create(Task task) {
        return this.jdbcTemplate.queryForObject(
                CREATE,
                TaskMapper::taskListMapper,
                task.getTitle(),
                task.getDescription(),
                task.getFiles()
        );
    }

    @Override
    public Task update(Task task) {
        return this.jdbcTemplate.queryForObject(
                UPDATE,
                TaskMapper::taskListMapper,
                task.getTitle(),
                task.getDescription(),
                task.getFiles(),
                task.getId()
        );
    }

    @Override
    public void deleteById(Long id) {
        this.jdbcTemplate.update(DELETE, id);
    }

}
