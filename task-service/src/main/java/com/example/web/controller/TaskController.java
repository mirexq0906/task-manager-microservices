package com.example.web.controller;

import com.example.model.Task;
import com.example.service.TaskService;
import com.example.web.dto.TaskDto;
import com.example.web.mapper.TaskMapper;
import com.example.web.response.TaskListResponse;
import com.example.web.response.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    @GetMapping
    public ResponseEntity<TaskListResponse> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.taskMapper.taskListToTaskListResponse(taskService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.taskMapper.taskToTaskResponse(taskService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody TaskDto taskDto) {
        Task task = this.taskService.create(
                this.taskMapper.requestToTask(taskDto)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.taskMapper.taskToTaskResponse(task)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        Task task = this.taskService.update(
                this.taskMapper.requestToTask(taskDto, id)
        );
        return ResponseEntity.status(HttpStatus.OK).body(
                this.taskMapper.taskToTaskResponse(task)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> delete(@PathVariable Long id) {
        this.taskService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
