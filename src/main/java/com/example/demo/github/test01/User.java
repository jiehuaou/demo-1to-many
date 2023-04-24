package com.example.demo.github.test01;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_category",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id",
                    referencedColumnName = "id"))
    private Set<Category00> categories=new HashSet<>();

    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Category00> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category00> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id!=null && Objects.equals(getId(), user.getId()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }

    public void addCategory(Category00 category) {
        categories.add(category);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", categories=" + categories +
                ", name='" + name + '\'' +
                '}';
    }
}
