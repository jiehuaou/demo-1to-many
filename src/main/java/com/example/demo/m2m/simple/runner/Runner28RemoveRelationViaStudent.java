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

/**
 * change relation via Student
 * 1, Student "Bob" add C++ and remove AWS, "Web 3.0", then save Student
 */
@Slf4j
@Order(28)
@Component("Runner28RemoveRelation")
public class Runner28RemoveRelationViaStudent implements CommandLineRunner {
    @Autowired
    StudentStore studentStore;
    @Autowired
    CourseStore courseStore;
    @Autowired
    Many2ManyService service;
    @Override
    public void run(String... args) throws Exception {
        log.info("-------- Runner28 Remove Relation Via Student begin -------------");

        // Student "Bob" Course add C++ and remove AWS, then save Student
        Student bob = studentStore.findByTitle("Bob").get(0);
        Course aws = courseStore.findFirstByName("AWS");
        Course web = courseStore.findFirstByName("Web 3.0");
        Course cpp2 = courseStore.findFirstByName("C++ Part 2");  // add

//        bob.removeCourse(aws.getId());       //  Bob remove Course "AWS"
//        bob.removeCourse(web.getId());       //  Bob remove    Course "Web"
        bob.removeCourseObject(aws);
        bob.removeCourseObject(web);
        bob.addCourse(cpp2);                 //  Bob add    Course "C++ Part 2"
        service.saveStudent(bob);

        log.info("-------- Runner28 Remove Relation Via Student end -------------");
        studentStore.findFirstByTitle("Bob").ifPresent(e->log.info("{}", e));
        log.info("-------- Runner28 Remove Relation Via Student check -------------");
    }
}
