package com.example.web.response;

import lombok.Data;

@Data
public class TaskResponse {

    private Long id;
    
    private String title;

    private String description;

    private String files;

    private String created_at;

    private String updated_at;

}
