package com.example.demo.uni.one2many;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * unidirectional associations
 *
 * The unidirectional associations are not very efficient when it comes to removing child entities.
 * Hibernate will delete all children then re-insert all others.
 *
 * company is parent,
 * branch is child,
 */
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(targetEntity=Branch.class,cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Branch> branch = new ArrayList<>();

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

    public List<Branch> getBranch() {
        return branch;
    }

    public void setBranch(List<Branch> branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", branch=" + branch +
                '}';
    }
}
