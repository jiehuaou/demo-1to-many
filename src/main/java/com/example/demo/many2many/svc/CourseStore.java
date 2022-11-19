package com.example.demo.many2many.svc;

import com.example.demo.many2many.data.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseStore extends CrudRepository<Course, Integer> {
    public List<Course> findByName(String name);
}
