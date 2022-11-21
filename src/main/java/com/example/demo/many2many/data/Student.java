package com.example.demo.many2many.data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "student")
public class Student  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

//    @Column(name = "description")
//    private String description;

//    @Column(name = "published")
//    private boolean published;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "course_like",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> likedCourses = new HashSet<>();

    public Student () {

    }

    public Student (String title) {
        this.title = title;
        //this.description = description;
//        this.published = published;
    }

    // getters and setters

    public void addCourse(Course course) {
        this.addCourse(course, true);
    }

    public void addCourse(Course course, boolean configCourse) {
        this.likedCourses.add(course);
        if(configCourse) {
            course.addStudent(this, false);
        }

    }

    public void removeCourse(long courseId) {
        removeCourse(courseId, true);
    }
    public void removeCourse(long courseId, boolean configCourse) {
        List<Course> removedCourseList = this.likedCourses.stream()
                .filter(t -> t.getId() == courseId)
                .collect(Collectors.toList());
        this.likedCourses.removeAll(removedCourseList);
        if(configCourse) {
            removedCourseList.stream().forEach(e -> e.removeStudent(this.getId(), false));
        }
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", title='" + title + '\'' +
              //  ", description='" + description + '\'' +
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        this.likedCourses = likedCourses;
    }

    public Optional<Course> findCource(Course course) {
        return likedCourses.stream()
                .filter(e->(e==course || e.getName().equalsIgnoreCase(course.getName())))
                .findFirst();
    }
}