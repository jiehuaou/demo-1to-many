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
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * remove association from Non-owner side - Course
 *      does not work,
 *          remove Student from Course,
 *          when entity instance is from same transaction
 */
@Slf4j
@Order(29)
@Component("Runner29RemoveRelation")
public class Runner29RemoveRelationViaCourse implements CommandLineRunner {
    @Autowired
    StudentStore studentStore;
    @Autowired
    CourseStore courseStore;
    @Autowired
    Many2ManyService service;
    @Override
    public void run(String... args) throws Exception {
        log.info("-------- Runner29_Remove_Relation_Via_Course begin -------------");

        // Course "Java" add Student "Bob" and remove "Joe", then save Course
//        Course aws = courseStore.findFirstByName("AWS");
//        Long joe = studentStore.findTop1ByTitle("Joe").getId();
//        Long lexi = studentStore.findTop1ByTitle("Lexi").getId();
//        List<Student> students = new ArrayList<>();
//        students.addAll(aws.removeStudent(joe));    // removing Student from Course, not work here
//        students.addAll(aws.removeStudent(lexi));
//        service.saveCourse(aws);        // does not work, deleting Student from  Course

        Course aws = courseStore.findFirstByName("AWS");
        service.removeStudentViaCourseFailed(aws, "Joe", "Lexi"); // not work,

        log.info("-------- Runner29_Remove_Relation_Via_Course end -------------");
        log.info("{}", courseStore.findFirstByName("AWS").toStringWithStudent());
        log.info("-------- Runner29_Remove_Relation_Via_Course check -------------");
    }
}
