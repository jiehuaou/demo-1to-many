package com.example.demo.one2many.svc;

import com.example.demo.one2many.data.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface QuestionStore extends JpaRepository<Question, Integer> {
    Optional<Question> findByQname(String qname);
}
