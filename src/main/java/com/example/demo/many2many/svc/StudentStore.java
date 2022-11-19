package com.example.demo.many2many.svc;

import com.example.demo.many2many.data.Course;
import com.example.demo.many2many.data.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentStore extends CrudRepository<Student, Integer> {
    public List<Student> findByTitle(String name);
}
