package com.example.demo.one2one.data;

import javax.persistence.*;

/**
 * the Category is the Child.
 * Demo to implement with a Join Table.
 */
@Entity
public class Category {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    private Long id;

    public Category() {
    }
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private String name;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @OneToOne(mappedBy = "category")  // "mappedBy" means the "foreign key" is in another table
    private Post post;


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", postId=" + post.getId() +
                '}';
    }
}
