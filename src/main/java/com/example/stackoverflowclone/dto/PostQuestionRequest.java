package com.example.stackoverflowclone.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostQuestionRequest {
    private String title;
    private String body;
    private List<String> tags;
    private Long userId;
}
