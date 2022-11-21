package com.example.demo.many2many.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table()
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "likedCourses" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

    public Course() {

    }

    public Course(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.addStudent(student, true);
    }

    public void addStudent(Student student, boolean configStudent) {
        this.students.add(student);
        if(configStudent) {
            student.addCourse(this, false);
        }
    }

    public void removeStudent(Long studentId) {
        removeStudent(studentId, true);
    }

    /**
     * remove Student by StudentId,
     * @param studentId
     * @param configStudent
     */
    public void removeStudent(Long studentId, boolean configStudent) {
        List<Student> removedStudentList = this.students.stream()
                .filter(e -> e.getId()==studentId)
                .collect(Collectors.toList());
        students.removeAll(removedStudentList);
        if(configStudent) {
            removedStudentList.stream().forEach(e->e.removeCourse(this.getId(), false));
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String toStringWithStudent() {
        String studentText = students.stream().map(e->e.toLimitString()).collect(Collectors.joining("|"));
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students='" + studentText + '\'' +
                '}';
    }



    public void setId(long id) {
        this.id = id;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
