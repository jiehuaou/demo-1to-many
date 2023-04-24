package com.example.demo.many2many.svc;

import com.example.demo.many2many.data.Course;
import com.example.demo.many2many.data.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
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
    public void addMultipleCourseFromCourse(Student newStudent, String ...courseNames) {
        Student bob = studentStore.save(newStudent);

        List<String> result = Arrays.stream(courseNames).peek(name->{
            Course c1 = courseStore.findFirstByName(name);
            c1.addStudent(bob);
            courseStore.save(c1);
        }).collect(Collectors.toList());
        System.out.println("add courseNames : " + result);

//        Course c2 = courseStore.findFirstByName(course2);
//        c2.addStudent(bob);
//        courseStore.save(c2);
        System.out.println("\t\t ---end Transaction---");
    }

    /**
     * create new Student() and associate with 2 Course, then save( Student )
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

    /**
     * create new non-owner with multiple association
     * new Course with 2 existing Students in one save()
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addMultipleStudentViaNewCourse(Course newCourse, String student1, String student2) {
        Student s1 = studentStore.findTop1ByTitle(student1);
        Student s2 = studentStore.findTop1ByTitle(student2);
        newCourse.addStudent(s1);
        newCourse.addStudent(s2);
        Course course = courseStore.save(newCourse);
        System.out.println("\t\t ---end Transaction---" + course);
    }

    /**
     * create new non-owner with multiple association
     * save new Course
     * associate one existing Students
     * associate another existing Students
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addMultipleStudent(Course newCourse, String student1, String student2) {
        // save course first
        Course course = courseStore.save(newCourse);

        // save Student1
        Student s1 = studentStore.findTop1ByTitle(student1);
        s1.addCourse(course);
        studentStore.save(s1);

        // save Student2
        Student s2 = studentStore.findTop1ByTitle(student2);
        s2.addCourse(course);
        studentStore.save(s1);

        System.out.println("\t\t ---end Transaction---" + course);
    }

    /**
     * remove association from Non-owner side - Course
     *      it is OK, remove Student from Course,
     *          when loading everything inside transaction
     *
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeStudentViaCourse(String course, String ...students) {
        Course aws = courseStore.findFirstByName(course);

        List<Student> removed = new ArrayList<>();
        Arrays.stream(students).forEach(s -> {
            studentStore.findFirstByTitle(s).stream()
                    .map(student -> student.getId())
                    .forEach(id->removed.addAll(aws.removeStudent(id)));
        });
        courseStore.save(aws);        // it is OK, remove Student from Course, when loading everything inside trans
        log.info("---remove student--- {}", removed );
        log.info("---Course state--- {}", aws.toStringWithStudent() );
        log.info("---end Transaction---" );
    }

    /**
     * remove association from Non-owner side - Course
     *      does not work, remove Student from Course,
     *          when entity instance is from same transaction
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeStudentViaCourseFailed(Course course, String ...students) {

        List<Student> removed = new ArrayList<>();
        Arrays.stream(students).forEach(e -> {
            studentStore.findFirstByTitle(e).stream()
                    .map(student -> student.getId())
                    .forEach(id->removed.addAll(course.removeStudent(id)));
        });
        Course course1 = courseStore.save(course);        // does not work, deleting Student from  Course
        log.info("---Course state--- {}", course1.toStringWithStudent() );
        log.info("---end Transaction---" );
    }

    @Transactional(readOnly = true)
    public Long getStudentStream(Consumer<Student> consumer) {
        return studentStore
                .findAllAsStream()
                .peek(consumer)
                .count();
    }

}
