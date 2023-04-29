package com.example.demo.m2m.simple.runner;

import com.example.demo.m2m.simple.data.Student;
import com.example.demo.m2m.simple.svc.CourseStore;
import com.example.demo.m2m.simple.svc.Many2ManyService;
import com.example.demo.m2m.simple.svc.StudentStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.stream.StreamSupport;

/**
 * add relation - create new student with existing course
 *
 * to avoid the "detached entity passed to persist" Exception,
 * try to save entity inside transaction.
 *
 *     since inside transaction, Course is fetched in managed state.
 *
 */
@Slf4j
@Order(16)
@Component("PartialCreateStudentCourse16")
public class Runner16PartialCreateOK implements CommandLineRunner {
    @Autowired
    StudentStore studentStore;
    @Autowired
    CourseStore courseStore;
    @Autowired
    Many2ManyService service;
    @Override
    public void run(String... args) throws Exception {
        log.info("-------- Runner16 Partial Create OK begin -------------");

        // create new student with 2 existing course
        // inside one transaction
        service.addMultipleCourseFromCourse(new Student("Bob"), "AWS", "Web 3.0", "Android");
        service.addMultipleCourseFromStudent(new Student("Bob 2"), "AWS", "Web 3.0");

        log.info("-------- Runner16 Partial Create OK end -------------");
        StreamSupport.stream(studentStore.findAll().spliterator(), false)
                .forEach(e -> log.info(e.toString()));
        log.info("-------- Runner16 Partial Create OK check -------------");
    }
}
