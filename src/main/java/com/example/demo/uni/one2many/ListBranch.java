package com.example.demo.uni.one2many;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * unidirectional associations
 *
 * company is parent,
 * branch is child,
 */
@Entity
public class ListBranch {

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
        if (!(o instanceof ListBranch)) return false;
        ListBranch branch = (ListBranch) o;
        return getId()!=null && getId()==branch.getId();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    public ListBranch() {
    }

    public ListBranch(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ListBranch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
