package com.example.demo.many2many.svc;

import com.example.demo.many2many.data.ManyQuestion;
import org.springframework.data.repository.CrudRepository;

public interface MyManyStore extends CrudRepository<ManyQuestion, Integer> {
}
