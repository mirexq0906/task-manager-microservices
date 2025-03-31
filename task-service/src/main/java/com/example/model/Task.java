package com.example.model;

import lombok.Data;

@Data
public class Task {

    private Long id;

    private String title;

    private String description;

    private String files;

    private String updated_at;

    private String created_at;

}
