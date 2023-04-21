package com.example.demo.many2many.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Course is the Inverse side
 */
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "likedCourses" , cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

    public Course() {

    }

    public Course(String name) {
        this.name = name;
    }

    public Long getId() {
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
        this.getStudents().add(student);
        student.getLikedCourses().add(this);
    }

//    public void addStudent(Student student, boolean configStudent) {
//
//        if(configStudent) {
//            student.addCourse(this, false);
//        }
//    }

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



    public void setId(Long id) {
        this.id = id;
    }

    public void setStudents(Set<Student> students) {
        this.students = new HashSet<>(students);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getId() != null && getId().equals(course.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }
}
