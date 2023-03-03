package com.example.demo.uni.one2many;

import javax.persistence.*;

/**
 * unidirectional associations
 *
 * company is parent,
 * branch is child,
 */
@Entity
public class Branch {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    public Branch() {
    }

    public Branch(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
