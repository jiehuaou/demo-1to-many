package com.example.demo.many2many.data;

import javax.persistence.Column;

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

    public StudentDTO(Integer age, String title) {
        this.age = age;
        this.title = title;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "age=" + age +
                ", title='" + title + '\'' +
                '}';
    }
}
