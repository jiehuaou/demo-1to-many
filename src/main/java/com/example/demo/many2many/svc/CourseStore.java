package com.example.demo.many2many.svc;

import com.example.demo.many2many.data.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CourseStore extends JpaRepository<Course, Long> {
    public Course findFirstByName(String name);

    public Optional<Course> findOneByName(String name);
}
