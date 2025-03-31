package com.example.repository.mapper;

import com.example.model.Task;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TaskMapper {

    public static Task taskListMapper(ResultSet resultSet, int rowNum) throws SQLException {
        Task task = new Task();
        task.setId(resultSet.getLong("id"));
        task.setTitle(resultSet.getString("title"));
        task.setDescription(resultSet.getString("description"));
        task.setFiles(resultSet.getString("files"));
        task.setCreated_at(resultSet.getString("created_at"));
        task.setUpdated_at(resultSet.getString("updated_at"));
        return task;
    }

    public static Optional<Task> taskMapper (ResultSet resultSet, int rowNum) throws SQLException {
        Task task = taskListMapper(resultSet, rowNum);
        return Optional.of(task);
    }

}
