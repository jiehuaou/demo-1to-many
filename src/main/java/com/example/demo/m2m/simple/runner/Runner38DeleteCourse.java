package com.example.demo.m2m.simple.runner;

import com.example.demo.m2m.simple.data.Course;
import com.example.demo.m2m.simple.svc.CourseStore;
import com.example.demo.m2m.simple.svc.Many2ManyService;
import com.example.demo.m2m.simple.svc.StudentStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * delete Course - need to remove reference from course before deletion,
 *                  or otherwise nothing happens
 */
@Slf4j
@Order(38)
@Component("Runner38DeleteCourse")
public class Runner38DeleteCourse implements CommandLineRunner {
    @Autowired
    StudentStore studentStore;
    @Autowired
    CourseStore courseStore;
    @Autowired
    Many2ManyService service;
    @Override
    public void run(String... args) throws Exception {
        log.info("-------- Runner38 Delete Course begin -------------");

        final String javaCourse = "java";
        //
        Course java = courseStore.findFirstByName(javaCourse);
        //courseStore.delete(java);
        service.deleteCourse(java);   // need to remove reference from Student before deletion,

        log.info("-------- Runner38 Delete Course end -------------");
        courseStore.findOneByName(javaCourse)
                .ifPresentOrElse(e->log.info("{}", e),
                        ()->log.info("Course {} is deleted", javaCourse) );
        log.info("-------- Runner38 Delete Course check -------------");
    }
}
