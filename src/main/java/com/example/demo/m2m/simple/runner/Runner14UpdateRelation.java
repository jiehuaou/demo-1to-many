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

import java.util.Optional;

/**
 * add relation between existing student and existing course
 */
@Slf4j
@Order(14)
@Component("Runner14UpdateRelation")
public class Runner14UpdateRelation implements CommandLineRunner {
    @Autowired
    StudentStore studentStore;
    @Autowired
    CourseStore courseStore;
    @Autowired
    Many2ManyService service;
    @Override
    public void run(String... args) throws Exception {
        log.info("-------- update-course update begin -------------");

        // existing Student "Joe" like existing Course "AWS"
        Optional<Student> joe = studentStore.findFirstByTitle("Joe");
        joe.ifPresent(e->{
            Course aws = courseStore.findFirstByName("AWS");
            Course android = courseStore.findFirstByName("Android");
            e.addCourse(aws);
            e.addCourse(android);
            service.saveStudent(e);
        });


        // existing Student “Tiger” like existing Course “Web”
        Student tiger = studentStore.findTop1ByTitle("Tiger");
        Course web = courseStore.findFirstByName("Web 3.0");
        Student kate = studentStore.findTop1ByTitle("Kate");
        web.addStudent(tiger);
        web.addStudent(kate);
        service.saveCourse(web);

        log.info("-------- update-course update end -------------");
        studentStore.findFirstByTitle("Joe").ifPresent(e->log.info("{}", e));
        courseStore.findOneByName("Web 3.0").ifPresent(e->log.info("{}", e.toStringWithStudent()));
        log.info("-------- update-course check  end -------------");
    }
}
