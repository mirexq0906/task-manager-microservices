package com.example.service;

import com.example.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> findAll();

    Task findById(Long id);

    Task create(Task task);

    Task update(Task task);

    void deleteById(Long id);

}
