package com.example.demo.many2many.data;

import java.util.*;

public class StudentDTO {
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private Integer age = null;
    private String title;
    private List<CourseDTO> likedCourses = new ArrayList<>();

    public StudentDTO(Integer age, String title) {
        this.age = age;
        this.title = title;
    }

    public void setLikedCourses(List<CourseDTO> likedCourses) {
        this.likedCourses.addAll(likedCourses);
    }

    public void addCourse(CourseDTO courseDTO) {
        this.likedCourses.add(courseDTO);
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "age=" + age +
                ", title='" + title + '\'' +
                ", likedCourses='" + likedCourses + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentDTO)) return false;
        StudentDTO that = (StudentDTO) o;
        return Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }
}
