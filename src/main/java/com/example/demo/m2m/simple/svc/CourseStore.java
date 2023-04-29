package com.example.demo.m2m.simple.svc;

import com.example.demo.m2m.simple.data.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseStore extends JpaRepository<Course, Long> {
    public Course findFirstByName(String name);

    public Optional<Course> findOneByName(String name);
}
