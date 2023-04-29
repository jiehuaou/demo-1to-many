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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * update property from either student or course
 */
@Slf4j
@Order(22)
@Component("Runner22UpdateBoth")
public class Runner22UpdateBoth implements CommandLineRunner {
    @Autowired
    StudentStore studentStore;
    @Autowired
    CourseStore courseStore;
    @Autowired
    Many2ManyService service;
    @Override
    public void run(String... args) throws Exception {
        log.info("-------- Runner22_Update_Both begin -------------");


        // existing Student “Tiger” like existing Course “Web”
        updateBoth1ViaStudent();
        log.info("-------- Runner22_Update_Both end -------------");
        studentStore.findFirstByTitle("Kate").ifPresent(e->log.info("{}", e));
        courseStore.findOneByName("Web 3.0").ifPresent(e->log.info("{}", e.toStringWithStudent()));
        log.info("-------- Runner22_Update_Both  end -------------");

        updateBoth2ViaCourse();

        log.info("-------- Runner22_Update_Both end -------------");
        studentStore.findFirstByTitle("Kate").ifPresent(e->log.info("{}", e));
        courseStore.findOneByName("Web 3.0").ifPresent(e->log.info("{}", e.toStringWithStudent()));
        log.info("-------- Runner22_Update_Both  end -------------");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void updateBoth1ViaStudent() {
        Course web = courseStore.findFirstByName("Web 3.0");
        Student kate = studentStore.findTop1ByTitle("Kate");
        kate.setAge(30);
        web.setSchedule("3 week");
        kate.addCourse(web);
        service.saveStudent(kate);
    }

    void updateBoth2ViaCourse() {
        Course web = courseStore.findFirstByName("Web 3.0");
        Student kate = studentStore.findTop1ByTitle("Kate");
        kate.setAge(40);
        web.setSchedule("4 week");
        kate.addCourse(web);
        service.saveCourse(web);
    }
}
