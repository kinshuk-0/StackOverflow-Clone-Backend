package com.example.stackoverflowclone.controller;

import com.example.stackoverflowclone.dto.AcceptAnswerRequest;
import com.example.stackoverflowclone.dto.PostAnswerRequest;
import com.example.stackoverflowclone.entity.Answer;
import com.example.stackoverflowclone.entity.Question;
import com.example.stackoverflowclone.entity.User;
import com.example.stackoverflowclone.repository.QuestionRepository;
import com.example.stackoverflowclone.repository.UserRepository;
import com.example.stackoverflowclone.service.AnswerService;
import com.example.stackoverflowclone.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
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

    @Autowired
    private QuestionService questionService;

    @PostMapping("/post")
    public ResponseEntity<?> postQuestion(@RequestBody PostAnswerRequest postAnswerRequest) {
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

    @PutMapping("/accept")
    public ResponseEntity<?> markAnswerAsAccepted(
            @RequestBody AcceptAnswerRequest acceptAnswerRequest
            ) {
        Question question = questionService.getQuestionById(acceptAnswerRequest.getQuestionId());
        Answer answer = answerService.getAnswerById(acceptAnswerRequest.getAnswerId());

        if(question == null) {
            return new ResponseEntity<>("Invalid question id", HttpStatus.NOT_ACCEPTABLE);
        }
        if(answer == null) {
            return new ResponseEntity<>("Invalid answer id", HttpStatus.NOT_ACCEPTABLE);
        }
        if(!Objects.equals(answer.getQuestion().getQuestionId(), question.getQuestionId())) {
            return new ResponseEntity<>("Invalid ids", HttpStatus.NOT_ACCEPTABLE);
        }

        question.setAcceptedAnswer(answer.getAnswerId());
        questionService.editQuestion(question);

        answer.setAccepted(true);
        answerService.editAnswer(answer);

        return ResponseEntity.ok("Answer marked as accepted");
    }
}
