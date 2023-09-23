package com.example.stackoverflowclone.service;

import com.example.stackoverflowclone.entity.Answer;
import com.example.stackoverflowclone.entity.Question;

public interface AnswerService {
    Answer postAnswer(Answer answer);
    Answer getAnswerById(Long answerId);
    Answer editAnswer(Answer answer);
}
