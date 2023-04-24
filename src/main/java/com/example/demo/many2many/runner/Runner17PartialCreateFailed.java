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
 * add relation - create new course  with existing student
 *
 * demo InvalidDataAccessApiUsageException:
 *          * detached entity passed to persist :
 *
 *          since the Student is detached and passed to persist.
 *
 */
@Slf4j
@Order(17)
@Component("PartialCreateStudentCourse17")
public class Runner17PartialCreateFailed implements CommandLineRunner {
    @Autowired
    StudentStore studentStore;
    @Autowired
    CourseStore courseStore;
    @Autowired
    Many2ManyService service;
    @Override
    public void run(String... args) throws Exception {
        log.info("-------- Partial-Create-StudentCourse begin -------------");


        // create new course with 2 existing student
        /**
         * InvalidDataAccessApiUsageException:
         * detached entity passed to persist: com.example.demo.many2many.data.Student
         */
        try {
            Course cpp = new Course("C++");
            cpp.addStudent(studentStore.findFirstByTitle("Lexi").get());
            cpp.addStudent(studentStore.findFirstByTitle("Joe").get());
            service.saveCourse(cpp);  // InvalidDataAccessApiUsageException happen here
        } catch (Exception e) {
            log.error("*** should not work here ***", e);
        }

        log.info("-------- Partial-Create-StudentCourse end -------------");
        StreamSupport.stream(studentStore.findAll().spliterator(), false)
                .forEach(e -> log.info(e.toString()));
        log.info("-------- Partial-Create-StudentCourse check -------------");
    }
}
