package com.example.demo.one2one.data;

import javax.persistence.*;

/**
 * The Post entity plays the Parent role and the PostDetails is the Child.
 */
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "post", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private PostDetails details;

    public Long getId() {
        return id;
    }

    public PostDetails getDetails() {
        return details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDetails(PostDetails details) {
        this.details = details;
        details.setPost(this);
    }

    public void removeDetails(PostDetails details) {
        if (details != null) {
            details.setPost(null);
        }
        this.details = null;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details=" + details +
                '}';
    }
}
