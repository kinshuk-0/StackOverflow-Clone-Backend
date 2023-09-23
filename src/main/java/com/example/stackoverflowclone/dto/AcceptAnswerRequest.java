package com.example.stackoverflowclone.dto;

import lombok.Data;

@Data
public class AcceptAnswerRequest {
    private Long answerId;
    private Long questionId;
}
