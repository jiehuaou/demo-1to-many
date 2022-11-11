package com.example.demo.one2one.data;

import com.example.demo.one2one.svc.PostEeventListener;

import javax.persistence.*;

/**
 * The Post entity plays the Parent role and the PostDetails is the Child.
 */
@EntityListeners(PostEeventListener.class)
@Entity
public class Post {

    public Post() {
    }

    public Post(Long id, Long version, String name, String owner, PostDetails details) {
        this.version = version;
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.details = details;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Version
    private Long version;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

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
                "version=" + version +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", details=" + details +
                '}';
    }
}
