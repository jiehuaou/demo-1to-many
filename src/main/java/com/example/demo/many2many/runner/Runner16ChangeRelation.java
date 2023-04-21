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
 * change relation
 * 1, Student "Bob" add C++ and remove AWS, then save Student
 * 2, Course "Java" add Student "Bob" and remove "Joe", then save Course
 *       ** removing Student from Course (non-owner side), not work here
 */
@Slf4j
@Order(16)
@Component("ChangeRelationStudentCourse")
public class Runner16ChangeRelation implements CommandLineRunner {
    @Autowired
    StudentStore studentStore;
    @Autowired
    CourseStore courseStore;
    @Autowired
    Many2ManyService service;
    @Override
    public void run(String... args) throws Exception {
        log.info("-------- Change-Relation-Student-Course begin -------------");

        // Student "Bob" Course add C++ and remove AWS, then save Student
        Student bob = studentStore.findByTitle("Bob").get(0);
        Course aws = courseStore.findFirstByName("AWS");
        Course cpp = courseStore.findFirstByName("C++");

        bob.removeCourse(aws.getId());      //  Bob remove Course "AWS"
        bob.addCourse(cpp);                 //  Bob add    Course "C++"
        Student bob2 = service.saveStudent(bob);   // Student is Owner of relationship, Only Owner can remove the relationship

        // Course "Java" add Student "Bob" and remove "Joe", then save Course
        Course java = courseStore.findFirstByName("java");
        Student joe = studentStore.findByTitle("Joe").get(0);
        java.removeStudent(joe.getId());    // removing Student from Course, not work here
        java.addStudent(bob2);            // Student can be added from Course,
        service.saveCourse(java);        // does not work, deleting Student from  Course

        log.info("-------- Change-Relation-Student-Course end -------------");
        StreamSupport.stream(studentStore.findAll().spliterator(), false).forEach(e -> log.info(e.toString()));
        log.info("-------- Change-Relation-Student-Course check -------------");
    }
}
