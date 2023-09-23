package com.example.stackoverflowclone.service;

import com.example.stackoverflowclone.entity.Question;

public interface QuestionService {
    Question postQuestion(Question question);
    Question getQuestionById(Long questionId);

    Question editQuestion(Question question);
}
