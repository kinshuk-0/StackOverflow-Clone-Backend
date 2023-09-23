package com.example.stackoverflowclone.controller;

import com.example.stackoverflowclone.dto.PostAnswerRequest;
import com.example.stackoverflowclone.dto.PostQuestionRequest;
import com.example.stackoverflowclone.entity.Answer;
import com.example.stackoverflowclone.entity.Question;
import com.example.stackoverflowclone.entity.User;
import com.example.stackoverflowclone.repository.AnswerRepository;
import com.example.stackoverflowclone.repository.QuestionRepository;
import com.example.stackoverflowclone.repository.UserRepository;
import com.example.stackoverflowclone.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/answer")
public class AnswersController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/post")
    public ResponseEntity<?> postQuestion(@RequestBody PostAnswerRequest postAnswerRequest) {
        // Check if the user with the given userId exists
        Optional<User> user = userRepository.findById(postAnswerRequest.getUserId());
        Optional<Question> question = questionRepository.findById(postAnswerRequest.getQuestionId());

        if(user.isEmpty()) {
            return new ResponseEntity<>("User id doesn't exist", HttpStatus.NOT_ACCEPTABLE);
        }
        if(question.isEmpty()) {
            return new ResponseEntity<>("Invalid question id", HttpStatus.NOT_ACCEPTABLE);
        }

        Answer answer = new Answer();
        answer.setBody(postAnswerRequest.getBody());
        answer.setUser(user.get());
        answer.setQuestion(question.get());

        Answer savedQuestion = answerService.postAnswer(answer);

        return ResponseEntity.ok(savedQuestion);
    }
}
