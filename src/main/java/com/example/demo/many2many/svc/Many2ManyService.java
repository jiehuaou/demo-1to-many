package com.example.demo.many2many.svc;

import com.example.demo.many2many.data.Course;
import com.example.demo.many2many.data.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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

    public void saveAll(Student ...students) {
        Arrays.stream(students).forEach(e-> studentStore.save(e));

    }

    public Course saveCourse(Course course) {
        return courseStore.save(course);
    }

}
