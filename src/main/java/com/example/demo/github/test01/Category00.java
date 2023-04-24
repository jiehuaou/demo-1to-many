package com.example.demo.github.test01;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity()
public class Category00 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany(mappedBy = "categories",cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    Set<User> users = new HashSet<>();

    private String caption;

    public Category00() {
    }

    public Category00(String caption) {
        this.caption = caption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category00)) return false;
        Category00 category = (Category00) o;
        return id!=null && Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }

    public void addUser(User user) {
        users.add(user);
    }

    @PreRemove
    public void removeUsers() {
        users.forEach(user->{
            user.getCategories().removeIf(e->e.equals(this));
        });

    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
//                ", users=" + users.size() +
                ", caption='" + caption + '\'' +
                '}';
    }
}
