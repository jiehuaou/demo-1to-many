package com.example.demo.many2many.data;

import java.util.Objects;

public class CourseDTO {
    private String name;

    public CourseDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "name='" + name + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseDTO)) return false;
        CourseDTO courseDTO = (CourseDTO) o;
        return Objects.equals(getName(), courseDTO.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getClass());
    }
}
