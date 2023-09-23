package com.example.stackoverflowclone.dto;

import lombok.Data;

@Data
public class PostAnswerRequest {
    private Long questionId;
    private String body;
    private Long userId;
}
