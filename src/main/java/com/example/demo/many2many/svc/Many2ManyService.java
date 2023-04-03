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

    public Student saveStudent(Student student) {
        // ...
        return studentStore.save(student);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveAll(Student ...students) {
        Arrays.stream(students).forEach(e-> studentStore.save(e));

    }

    public Course saveCourse(Course course) {
        return courseStore.save(course);
    }

    @Transactional(readOnly = true)
    public Long getStudentStream(Consumer<Student> consumer) {
        return studentStore
                .findAllAsStream()
                .peek(consumer)
                .count();
    }

}
