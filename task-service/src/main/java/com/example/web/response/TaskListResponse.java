package com.example.web.response;

import lombok.Data;

import java.util.List;

@Data
public class TaskListResponse {

    private List<TaskResponse> tasks;

    @Data
    public static class TaskResponse {

        private Long id;

        private String title;

        private String description;

        private String files;

        private String created_at;

        private String updated_at;
    }

}
