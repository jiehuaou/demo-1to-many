package com.example.demo.many2many.runner;

import com.example.demo.many2many.data.Course;
import com.example.demo.many2many.data.Student;
import com.example.demo.many2many.svc.CourseStore;
import com.example.demo.many2many.svc.Many2ManyService;
import com.example.demo.many2many.svc.StudentStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.stream.StreamSupport;

/**
 * add relation
 * create new course  with existing student
 * create new student with existing course
 *
 * to avoid the "detached entity passed to persist" Exception,
 * try to save the existing Entity after configure relation-ship with new Object.
 *
 */
@Slf4j
@Order(15)
@Component("PartialCreateStudentCourse")
public class Runner15PartialCreate implements CommandLineRunner {
    @Autowired
    StudentStore studentStore;
    @Autowired
    CourseStore courseStore;
    @Autowired
    Many2ManyService service;
    @Override
    public void run(String... args) throws Exception {
        log.info("-------- Partial-Create-StudentCourse begin -------------");

        // create new student with 2 existing course
        // by transient object
        Student bob = new Student("Bob");
        bob.addCourse(courseStore.findFirstByName("AWS")).addCourse(courseStore.findFirstByName("Web 3.0"));
        service.saveStudent(bob);

        // inside transaction
        service.addMultipleCourseFromCourse(new Student("Bob 1"), "AWS", "Web 3.0");
        service.addMultipleCourseFromStudent(new Student("Bob 2"), "AWS", "Web 3.0");
        //bob.addCourse(web);
        //bob.setTitle("Bob2");
        // bob = service.saveStudent(bob);  // can not save student here otherwise throw "detached entity passed to persist" Exception

        // create new course with existing student
        Student lexi = studentStore.findFirstByTitle("Lexi").get();
        Course cpp = new Course("C++");
        lexi.addCourse(cpp);
        service.saveStudent(lexi);  // can not save Course here otherwise throw "detached entity passed to persist" Exception

        log.info("-------- Partial-Create-StudentCourse end -------------");
        StreamSupport.stream(studentStore.findAll().spliterator(), false)
                .forEach(e -> log.info(e.toString()));
        log.info("-------- Partial-Create-StudentCourse check -------------");
    }
}
