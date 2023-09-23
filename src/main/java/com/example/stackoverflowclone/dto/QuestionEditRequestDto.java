package com.example.stackoverflowclone.dto;

import lombok.Data;

@Data
public class QuestionEditRequestDto {
    private String title;
    private String body;
    private Long questionId;
    private Long userId;
}
