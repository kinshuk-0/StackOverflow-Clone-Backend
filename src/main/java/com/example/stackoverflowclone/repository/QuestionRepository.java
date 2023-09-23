package com.example.stackoverflowclone.repository;

import com.example.stackoverflowclone.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
