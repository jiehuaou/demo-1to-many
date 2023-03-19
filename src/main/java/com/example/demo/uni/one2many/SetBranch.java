package com.example.demo.uni.one2many;

import javax.persistence.*;
import java.util.Objects;

/**
 * unidirectional associations
 *
 * company is parent,
 * branch is child,
 */
@Entity
public class SetBranch {

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



    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SetBranch)) return false;
        SetBranch branch = (SetBranch) o;
        return getId()!=null && getId()==branch.getId();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    public SetBranch() {
    }

    public SetBranch(String name) {
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
