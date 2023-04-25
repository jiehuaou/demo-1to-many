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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    private String schedule;

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

    /**
     * add student and insure replace it if exist
     */
    public void addStudent(Student student) {
        if(!this.getStudents().add(student)) {
            this.getStudents().remove(student);
            this.getStudents().add(student);
        }
        if (!student.getLikedCourses().add(this)) {
            this.getStudents().remove(student);
            this.getStudents().add(student);
        }
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

    @PreRemove
    private void cleanAssociated() {
        students.forEach(student->{
            student.getLikedCourses().removeIf(c->c.equals(this));
        });
    }



    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", schedule='" + schedule + '\'' +
                '}';
    }

    public String toStringWithStudent() {
        String studentText = students.stream().map(e->e.toLimitString()).collect(Collectors.joining("|"));
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", schedule='" + schedule + '\'' +
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
        return Objects.hash(Course.class);
    }
}
