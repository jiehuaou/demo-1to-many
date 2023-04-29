package com.example.demo.m2m.simple.runner;


import com.example.demo.m2m.simple.data.Course;
import com.example.demo.m2m.simple.data.Student;
import com.example.demo.m2m.simple.svc.CourseStore;
import com.example.demo.m2m.simple.svc.Many2ManyService;
import com.example.demo.m2m.simple.svc.StudentStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Student is the Owner side
 * Course is the Inverse side
 *
 * add relation with new student and new course
 */
@Slf4j
@Order(13)
@Component("Runner13CreateNewNew")
public class Runner13CreateNewNew implements CommandLineRunner {

    @Autowired
    private StudentStore studentStore;
    @Autowired
    private CourseStore courseStore;
    @Autowired
    private Many2ManyService many2ManyService;
    @Override
    public void run(String... args) throws Exception {
        log.info("-------- many2many creator begin -------------");

//         create new student with new Course
        Student john = new Student("John");
        Student lexi = new Student("Lexi", 30);

        Course aws = new Course("AWS");
        Course web = new Course("Web 3.0");

        john.addCourse(aws);
        john.addCourse(web);

        lexi.addCourse(aws);

        many2ManyService.saveStudent(john);
        many2ManyService.saveStudent(lexi);

        // Lexi later add like "Web 3.0"
        lexi.addCourse(web);
        many2ManyService.saveStudent(lexi);

        Student kate = new Student("Kate");
        many2ManyService.saveStudent(kate);

        log.info("-------- many2many creator end 1-------------");
        List<Student> all = studentStore.findAll();
        all.forEach(e -> log.info(e.toString()));
        log.info("-------- many2many creator check 1-------------");

        // create new course with new student
        Course nodeJs = new Course("NodeJs");
        Course java=new Course("java");

        Student joe=new Student("Joe", 40);
        Student tiger=new Student("Tiger");

        nodeJs.addStudent(joe);
        nodeJs.addStudent(tiger);

        java.addStudent(joe);
        java.addStudent(tiger);

        many2ManyService.saveCourse(nodeJs);
        many2ManyService.saveCourse(java);

        Course android=new Course("Android");
        many2ManyService.saveCourse(android);


        log.info("-------- many2many creator end 2-------------");
        studentStore.findAll().forEach(e -> log.info(e.toString()));
        log.info("-------- many2many creator check 2 -------------");
    }
}
