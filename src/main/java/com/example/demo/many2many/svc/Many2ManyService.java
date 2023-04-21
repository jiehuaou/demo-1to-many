package com.example.demo.many2many.svc;

import com.example.demo.many2many.data.Course;
import com.example.demo.many2many.data.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Service
public class Many2ManyService {
    @Autowired
    StudentStore studentStore;

    @Autowired
    CourseStore courseStore;

    @Transactional(propagation = Propagation.REQUIRED)
    public Student saveStudent(Student student) {
        // ...
        return studentStore.save(student);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveAll(Student ...students) {
        Arrays.stream(students).forEach(e-> studentStore.save(e));

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Course saveCourse(Course course) {
        return courseStore.save(course);
    }

    /**
     * save(new Student), associate Course1 save(Course1), associate Course2 save(Course2)
     *
     * put all entity inside one transaction , to avoid:
     * exception "Multiple representations of the same entity"
     * exception "detached entity passed to persist"
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addMultipleCourseFromCourse(Student newStudent, String course1, String course2) {
        Student bob = studentStore.save(newStudent);
        Course c1 = courseStore.findFirstByName(course1);
        c1.addStudent(bob);
        courseStore.save(c1);
        Course c2 = courseStore.findFirstByName(course2);
        c2.addStudent(bob);
        courseStore.save(c2);
        System.out.println("\t\t ---end Transaction---");
    }

    /**
     * create new Student() and associate 2 Course, then save( Student )
     *
     * put all entity inside one transaction , to avoid:
     * exception "Multiple representations of the same entity"
     * exception "detached entity passed to persist"
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addMultipleCourseFromStudent(Student newStudent, String course1, String course2) {
        Course c1 = courseStore.findFirstByName(course1);
        Course c2 = courseStore.findFirstByName(course2);
        newStudent.addCourse(c1).addCourse(c2);
        Student bob = studentStore.save(newStudent);
        System.out.println("\t\t ---end Transaction---" + bob);
    }

    @Transactional(readOnly = true)
    public Long getStudentStream(Consumer<Student> consumer) {
        return studentStore
                .findAllAsStream()
                .peek(consumer)
                .count();
    }

}
