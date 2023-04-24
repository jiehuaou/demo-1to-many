package com.example.demo.many2many.data;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Student is the Owner side
 */
@Entity
@Table(name = "student")
public class Student  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "age")
    private Integer age = null;

    @Column(name = "title")
    private String title;

//    @Column(name = "description")
//    private String description;

//    @Column(name = "published")
//    private boolean published;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_like",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> likedCourses = new HashSet<>();   // use Set is better than List on Owner-Side

    public Student () {

    }

    public Student (String title) {
        this.title = title;
        //this.description = description;
//        this.published = published;
    }

    public Student (String title, Integer age) {
        this.title = title;
        this.age = age;
//        this.published = published;
    }

    // getters and setters

    public Student addCourse(Course course) {
        getLikedCourses().add(course);
        course.getStudents().add(this);
        //this.addCourse(course, true);
        return this;
    }

//    public void addCourse(Course course, boolean configCourse) {
//        this.likedCourses.add(course);
//        if(configCourse) {
//            course.addStudent(this, false);
//        }
//
//    }

    public void removeCourse(long courseId) {
         removeCourse(courseId, true);
    }
    public void removeCourse(long courseId, boolean configCourse) {
        List<Course> removedCourseList = this.likedCourses.stream()
                .filter(t -> t.getId().equals(courseId))
                .collect(Collectors.toList());
        this.likedCourses.removeAll(removedCourseList);
        if(configCourse) {
            removedCourseList.stream().forEach(e -> e.removeStudent(this.getId(), false));
        }
    }

    public void removeCourseObject(Course course) {
        this.likedCourses.remove(course);
        course.getStudents().remove(this);
    }

    private String toStringAge(Integer actual) {
        return actual==null? "Unknown" : actual.toString();
    }
    @Override
    public String toString() {
        return "  Student{" +
                "  id=" + id +
                ", title='" + title + '\'' +
                ", age=" + toStringAge(age) +
                ", likedCourses=" + likedCourses +
                '}';
    }

    public String toLimitString() {
        return "Student{" +
                "id=" + id +
                ", title='" + title + '\'' +
                //  ", description='" + description + '\'' +
                //", likedCourses=" + likedCourses +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Course> getLikedCourses() {
        return likedCourses;
    }

    public void setLikedCourses(Set<Course> likedCourses) {
        this.likedCourses = new HashSet<>(likedCourses);
    }

    public Optional<Course> findCourceByName(String courseName) {
        return likedCourses.stream()
                .filter(e->(courseName!=null && courseName.equals(e.getName())))
                .findFirst();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getId()!=null && getId().equals(student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Student.class);
    }
}