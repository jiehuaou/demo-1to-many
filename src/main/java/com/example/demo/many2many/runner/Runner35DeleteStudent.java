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

/**
 * delete Student - need to remove reference from course before deletion,
 *                  or otherwise nothing happens
 */
@Slf4j
@Order(35)
@Component("Runner35DeleteStudent")
public class Runner35DeleteStudent implements CommandLineRunner {
    @Autowired
    StudentStore studentStore;
    @Autowired
    CourseStore courseStore;
    @Autowired
    Many2ManyService service;
    @Override
    public void run(String... args) throws Exception {
        log.info("-------- Runner35 Delete Student begin -------------");

        //
        Student bob = studentStore.findByTitle("Bob").get(0);

        service.deleteStudent(bob);   // need to remove reference from course before deletion,

        log.info("-------- Runner35 Delete Student end -------------");
        studentStore.findFirstByTitle("Bob")
                .ifPresentOrElse(e->log.info("{}", e), ()->log.info("Student Bob is deleted") );
        log.info("-------- Runner35 Delete Student check -------------");
    }
}
