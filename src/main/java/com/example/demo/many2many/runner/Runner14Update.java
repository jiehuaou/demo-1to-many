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

import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * add relation with existing student and existing course
 */
@Slf4j
@Order(14)
@Component("UpdateStudentCourse")
public class Runner14Update implements CommandLineRunner {
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
            Course aws = courseStore.findByName("AWS").get(0);
            e.addCourse(aws);
            service.saveStudent(e);
        });


        // existing Student “Tiger” like existing Course “Web”
        Student tiger = studentStore.findTop1ByTitle("Tiger");
        Course web = courseStore.findByName("Web 3.0").get(0);
        web.addStudent(tiger);
        service.saveCourse(web);

        log.info("-------- update-course update end -------------");
//        StreamSupport.stream(courseStore.findAll().spliterator(), false).forEach(e -> log.info(e.toStringWithStudent()));
        StreamSupport.stream(studentStore.findAll().spliterator(), false)
                .forEach(e -> log.info(e.toString()));
        log.info("-------- update-course check  end -------------");
    }
}
