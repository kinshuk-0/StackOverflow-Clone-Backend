package com.example.stackoverflowclone.controller;

import com.example.stackoverflowclone.dto.PostQuestionRequest;
import com.example.stackoverflowclone.dto.QuestionEditRequestDto;
import com.example.stackoverflowclone.entity.Question;
import com.example.stackoverflowclone.entity.User;
import com.example.stackoverflowclone.repository.UserRepository;
import com.example.stackoverflowclone.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/question")
public class QuestionsController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/post")
    public ResponseEntity<?> postQuestion(@RequestBody PostQuestionRequest postQuestionRequest) {
        Optional<User> user = userRepository.findById(postQuestionRequest.getUserId());

        if(user.isEmpty()) {
            return new ResponseEntity<>("Username doesn't exist", HttpStatus.NOT_ACCEPTABLE);
        }

        Question question = new Question();
        question.setTitle(postQuestionRequest.getTitle());
        question.setBody(postQuestionRequest.getBody());
        question.setUser(user.get());
        question.setTags(postQuestionRequest.getTags());

        questionService.postQuestion(question);

        return ResponseEntity.ok("Operation Success");
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editQuestion(@RequestBody QuestionEditRequestDto questionEditRequestDto) {
        Question existingQuestion = questionService.getQuestionById(questionEditRequestDto.getQuestionId());

        if(existingQuestion == null) {
            return new ResponseEntity<>("Question doesn't exist", HttpStatus.NOT_ACCEPTABLE);
        }

        if(!Objects.equals(existingQuestion.getUser().getUser_id(), questionEditRequestDto.getUserId())) {
            return new ResponseEntity<>("Access denied", HttpStatus.NOT_ACCEPTABLE);
        }

        existingQuestion.setTitle(questionEditRequestDto.getTitle());
        existingQuestion.setBody(questionEditRequestDto.getBody());

        questionService.editQuestion(existingQuestion);

        return ResponseEntity.ok("Operation Success");
    }
}
