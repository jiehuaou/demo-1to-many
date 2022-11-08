package com.example.demo.one2many.svc;

import com.example.demo.one2many.data.Question;
import org.springframework.data.repository.CrudRepository;

public interface MyStore extends CrudRepository<Question, Integer> {
}
