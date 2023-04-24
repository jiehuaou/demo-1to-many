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

import java.util.ArrayList;
import java.util.List;

/**
 * remove association from Non-owner side - Course
 *         it is OK, remove Student from Course,
 *                when loading everything inside transaction
 */
@Slf4j
@Order(30)
@Component("Runner30RemoveRelation")
public class Runner30RemoveRelationViaCourse implements CommandLineRunner {
    @Autowired
    StudentStore studentStore;
    @Autowired
    CourseStore courseStore;
    @Autowired
    Many2ManyService service;
    @Override
    public void run(String... args) throws Exception {
        log.info("-------- Change-Relation-Student-Course begin -------------");

        // remove Student "Lexi" and "Joe", from Course "AWS"
        service.removeStudentViaCourse("AWS", "Joe", "Lexi");

        log.info("-------- Change-Relation-Student-Course end -------------");
        log.info("{}", courseStore.findFirstByName("AWS").toStringWithStudent());
        log.info("-------- Change-Relation-Student-Course check -------------");
    }
}
