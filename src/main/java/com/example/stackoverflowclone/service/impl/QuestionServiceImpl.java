package com.example.stackoverflowclone.service.impl;

import com.example.stackoverflowclone.entity.Question;
import com.example.stackoverflowclone.repository.QuestionRepository;
import com.example.stackoverflowclone.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    public Question postQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(Long questionId) {
        return questionRepository.getReferenceById(questionId);
    }

    @Override
    public Question editQuestion(Question question) {
        return questionRepository.save(question);
    }
}
