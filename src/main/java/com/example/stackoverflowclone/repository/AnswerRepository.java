package com.example.stackoverflowclone.repository;

import com.example.stackoverflowclone.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
