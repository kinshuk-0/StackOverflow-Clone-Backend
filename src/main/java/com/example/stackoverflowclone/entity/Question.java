package com.example.stackoverflowclone.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    private String title;
    private String body;

    @Column(name = "accepted_answer")
    private Long acceptedAnswer;

    @ElementCollection(targetClass = String.class)
    private List<String> tags;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
