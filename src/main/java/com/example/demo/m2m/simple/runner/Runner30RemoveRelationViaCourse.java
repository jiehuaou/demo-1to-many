package com.example.demo.m2m.simple.runner;

import com.example.demo.m2m.simple.svc.CourseStore;
import com.example.demo.m2m.simple.svc.Many2ManyService;
import com.example.demo.m2m.simple.svc.StudentStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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
        log.info("-------- Runner30_Remove_Relation_Via_Course begin -------------");

        // remove Student "Lexi" and "Joe", from Course "AWS"
        service.removeStudentViaCourse("AWS", "Joe", "Lexi");

        log.info("-------- Runner30_Remove_Relation_Via_Course end -------------");
        log.info("{}", courseStore.findFirstByName("AWS").toStringWithStudent());
        log.info("-------- Runner30_Remove_Relation_Via_Course check -------------");
    }
}
