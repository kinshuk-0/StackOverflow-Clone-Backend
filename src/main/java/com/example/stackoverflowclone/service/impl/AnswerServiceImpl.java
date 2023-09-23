package com.example.stackoverflowclone.service.impl;

import com.example.stackoverflowclone.entity.Answer;
import com.example.stackoverflowclone.entity.Question;
import com.example.stackoverflowclone.repository.AnswerRepository;
import com.example.stackoverflowclone.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Override
    public Answer postAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Answer getAnswerById(Long answerId) {
        return answerRepository.getReferenceById(answerId);
    }

    @Override
    public Answer editAnswer(Answer answer) {
        return answerRepository.save(answer);
    }
}
