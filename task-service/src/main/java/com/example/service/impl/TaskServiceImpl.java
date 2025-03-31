package com.example.service.impl;

import com.example.exception.EntityNotFoundException;
import com.example.model.Task;
import com.example.repository.TaskRepository;
import com.example.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return this.taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
    }

    @Override
    public Task create(Task task) {
        return this.taskRepository.create(task);
    }

    @Override
    public Task update(Task task) {
        Task updatedTask = this.findById(task.getId());
        updatedTask.setTitle(task.getTitle());
        updatedTask.setDescription(task.getDescription());
        updatedTask.setFiles(task.getFiles());
        return this.taskRepository.update(task);
    }

    @Override
    public void deleteById(Long id) {
        this.taskRepository.deleteById(id);
    }

}
