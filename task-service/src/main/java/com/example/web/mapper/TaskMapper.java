package com.example.web.mapper;

import com.example.model.Task;
import com.example.web.dto.TaskDto;
import com.example.web.response.TaskListResponse;

import com.example.web.response.TaskResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {

    public Task requestToTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setFiles(taskDto.getFiles());
        return task;
    }

    public Task requestToTask(TaskDto taskDto, Long id) {
        Task task = this.requestToTask(taskDto);
        task.setId(id);
        return task;
    }

    public TaskListResponse taskListToTaskListResponse(List<Task> taskList) {
        TaskListResponse taskListResponse = new TaskListResponse();

        List<TaskListResponse.TaskResponse> taskResponses = taskList.stream().map((item) -> {
            TaskListResponse.TaskResponse taskResponse = new TaskListResponse.TaskResponse();
            taskResponse.setId(item.getId());
            taskResponse.setTitle(item.getTitle());
            taskResponse.setDescription(item.getDescription());
            taskResponse.setFiles(item.getFiles());
            taskResponse.setCreated_at(item.getCreated_at());
            taskResponse.setUpdated_at(item.getUpdated_at());
            return taskResponse;
        }).toList();

        taskListResponse.setTasks(taskResponses);
        return taskListResponse;
    }

    public TaskResponse taskToTaskResponse(Task task) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTitle(task.getTitle());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setFiles(task.getFiles());
        taskResponse.setCreated_at(task.getCreated_at());
        taskResponse.setUpdated_at(task.getUpdated_at());
        return taskResponse;
    }

}
