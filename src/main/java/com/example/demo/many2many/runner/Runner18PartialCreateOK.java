package com.example.demo.many2many.runner;

import com.example.demo.many2many.data.Course;
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
 *
 * to avoid the "detached entity passed to persist" Exception,
 * try to save the existing Entity after configure relation-ship with new Object.
 *
 */
@Slf4j
@Order(18)
@Component("PartialCreateStudentCourse18")
public class Runner18PartialCreateOK implements CommandLineRunner {
    @Autowired
    StudentStore studentStore;
    @Autowired
    CourseStore courseStore;
    @Autowired
    Many2ManyService service;
    @Override
    public void run(String... args) throws Exception {
        log.info("-------- Runner18 Partial Create OK begin -------------");


        // create new course with 2 existing student
        // inside transaction
        Course cpp = new Course("C++");
        service.addMultipleStudentViaNewCourse(cpp, "Lexi", "Joe");

        Course cpp2 = new Course("C++ Part 2");
        service.addMultipleStudent(cpp2, "Lexi", "Joe");

        log.info("-------- Runner18 Partial Create OK end -------------");
        studentStore.findFirstByTitle("Joe").ifPresent(e->log.info("{}", e));
        studentStore.findFirstByTitle("Lexi").ifPresent(e->log.info("{}", e));
        log.info("-------- Runner18 Partial Create OK check -------------");
    }
}
