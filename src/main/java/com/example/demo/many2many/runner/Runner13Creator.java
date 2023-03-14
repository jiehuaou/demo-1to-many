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
 * Student is the Owner side
 * Course is the Inverse side
 *
 * add relation with new student and new course
 */
@Slf4j
@Order(13)
@Component("CreatorStudentCourse")
public class Runner13Creator implements CommandLineRunner {

    @Autowired
    private StudentStore studentStore;
    @Autowired
    private CourseStore courseStore;
    @Autowired
    private Many2ManyService many2ManyService;
    @Override
    public void run(String... args) throws Exception {
        log.info("-------- many2many creator begin -------------");

//         create student with new Course
        Student john = new Student("John");
        Student lexi = new Student("Lexi");
        Course aws = new Course("AWS");
        Course web = new Course("Web 3.0");

        john.addCourse(aws);
        john.addCourse(web);
        lexi.addCourse(aws);

        many2ManyService.saveStudent(john);
        many2ManyService.saveStudent(lexi);

        // Lexi later on like "Web 3.0"
        lexi.addCourse(web);
        many2ManyService.saveStudent(lexi);

        log.info("-------- many2many creator end 1-------------");
        Iterable<Student> all = studentStore.findAll();
        StreamSupport.stream(all.spliterator(), false).forEach(e -> log.info(e.toString()));
        log.info("-------- many2many creator check 1-------------");

        // create new course with new student
        Course nodeJs = new Course("NodeJs");
        Course java=new Course("java");

        Student joe=new Student("Joe");
        Student tiger=new Student("Tiger");

        nodeJs.addStudent(joe);
        nodeJs.addStudent(tiger);
        java.addStudent(joe);
        java.addStudent(tiger);

        many2ManyService.saveCourse(nodeJs);
        many2ManyService.saveCourse(java);


        log.info("-------- many2many creator end 2-------------");
        StreamSupport.stream(studentStore.findAll().spliterator(), false).forEach(e -> log.info(e.toString()));
        log.info("-------- many2many creator check 2 -------------");
    }
}
