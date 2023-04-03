package com.example.demo.many2many.runner;

import com.example.demo.many2many.data.Course;
import com.example.demo.many2many.data.Student;
import com.example.demo.many2many.data.StudentDTO;
import com.example.demo.many2many.data.StudentView;
import com.example.demo.many2many.svc.CourseStore;
import com.example.demo.many2many.svc.Many2ManyService;
import com.example.demo.many2many.svc.StudentStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * find student with like course,
 *
 * demo to use @EntityGraph(attributePaths = "likedCourses"), by which fetch association in one query,
 */
@Slf4j
@Order(19)
@Component("find student with like course")
public class Runner19QueryStudent implements CommandLineRunner {
    @Autowired
    StudentStore studentStore;
    @Autowired
    CourseStore courseStore;
    @Autowired
    Many2ManyService service;
    @Override
    public void run(String... args) throws Exception {
        log.info("-------- find student with like course begin -------------");

        Integer a = null;
        Integer b = null;
        boolean ret = Objects.equals(a, b);
        System.out.println("a==b? --> " + ret);

        List<Student> students = studentStore.findByTitle("Lexi");
        students.stream()
                .forEach(e -> log.info("{}", e.toString()));

        log.info("-------- find student View (interface) Projection -------------");

        List<StudentView> studentViews = studentStore.findAllView();
        studentViews.stream().forEach(e -> log.info("{}", this.text(e)));

        log.info("-------- find simple student DTO Projection -------------");
        List<StudentDTO> studentDTOS =  studentStore.findAllStudentDTO();
        studentDTOS.stream().forEach(e -> log.info("{}", e.toString()));

        log.info("-------- find complex student DTO Projection -------------");
        List<StudentDTO> complexStudentDTOS =  studentStore.findComplexStudentDTO();
        complexStudentDTOS.stream().forEach(e -> log.info("{}", e.toString()));

        log.info("-------- find student as stream -------------");
        service.getStudentStream(e -> log.info("{}", e.toString()));

        log.info("-------- find student with like course end -------------");

    }

    private String text(StudentView studentView) {
        Integer age = studentView.getAge();
        String title = studentView.getTitle();
        return String.format("StudentView{ age=%d, title=%s }", age, title);
    }
}
