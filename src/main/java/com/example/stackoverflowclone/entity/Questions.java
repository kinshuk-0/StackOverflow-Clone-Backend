package com.example.stackoverflowclone.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "questions")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long question_id;

    private String title;
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
