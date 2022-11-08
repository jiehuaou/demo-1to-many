package com.example.demo.many.one2many.svc;

import com.example.demo.many.one2many.data.Question;
import org.springframework.data.repository.CrudRepository;

public interface MyStore extends CrudRepository<Question, Integer> {
}
