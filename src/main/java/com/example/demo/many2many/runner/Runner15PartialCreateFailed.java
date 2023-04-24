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
 * add relation -  create new student with existing course
 *
 * demo InvalidDataAccessApiUsageException:
 *          detached entity passed to persist:
 *          since the Course is detached and passed to persist.
 *
 */
@Slf4j
@Order(15)
@Component("PartialCreateStudentCourse15")
public class Runner15PartialCreateFailed implements CommandLineRunner {
    @Autowired
    StudentStore studentStore;
    @Autowired
    CourseStore courseStore;
    @Autowired
    Many2ManyService service;
    @Override
    public void run(String... args) throws Exception {
        log.info("-------- Runner15 Partial Create Failed begin -------------");

        // create new student with 1 existing course, should not work
        try {
            Student jack = new Student("Jack");
            jack.addCourse(courseStore.findFirstByName("AWS"));
            service.saveStudent(jack);
        } catch (Exception e) {
            log.error("*** should not work ***", e);
        }

        // create new student with 2 existing course, should fail
        // by transient object

        /**
         * InvalidDataAccessApiUsageException:
         * detached entity passed to persist: com.example.demo.many2many.data.Course
         */
        try {
            Student bob = new Student("Bob");
            bob.addCourse(courseStore.findFirstByName("AWS"));
            bob.addCourse(courseStore.findFirstByName("Web 3.0"));
            service.saveStudent(bob);       // InvalidDataAccessApiUsageException should arise
        } catch (Exception e) {
            log.error("*** should not work ***", e);
        }


        log.info("-------- Runner15 Partial Create Failed end -------------");
        studentStore.findAll().forEach(e -> log.info(e.toString()));
        log.info("-------- Runner15 Partial Create Failed check -------------");
    }
}
