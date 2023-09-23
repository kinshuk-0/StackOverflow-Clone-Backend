package com.example.stackoverflowclone.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long answerId;

    private String body;
    private Boolean accepted;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
