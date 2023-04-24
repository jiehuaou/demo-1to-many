package com.example.demo.many2many.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;
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
    private List<Student> students = new ArrayList<>();

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

    public List<Student> getStudents() {
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

    public List<Student> removeStudent(Long studentId) {
        return removeStudent(studentId, true);
    }

    /**
     * remove Student by StudentId,
     * @param studentId
     * @param configStudent
     */
    public List<Student> removeStudent(Long studentId, boolean configStudent) {
        List<Student> removedStudentList = this.students.stream()
                .filter(e -> e.getId().equals(studentId))
                .collect(Collectors.toList());
        students.removeAll(removedStudentList);
        if(configStudent) {
            removedStudentList.forEach(e->e.removeCourse(this.getId(), false));
        }
        return removedStudentList;
    }

    public void removeStudentObject(Student student) {
        this.students.remove(student);
        student.getLikedCourses().remove(this);
    }

    public Optional<Student> findStudentByName(String studentName) {
        return students.stream()
                .filter(e->(studentName!=null && studentName.equals(e.getTitle())))
                .findFirst();
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

    public void setStudents(List<Student> students) {
        this.students = new ArrayList<>(students);
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
        return Objects.hash(Course.class);
    }
}
