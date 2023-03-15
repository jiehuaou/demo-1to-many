package com.example.demo.one2many.svc;

import com.example.demo.one2many.data.Answer;
import com.example.demo.one2many.data.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerStore extends JpaRepository<Answer, Integer> {
    Optional<Answer> findByAnswername(String answername);
}
