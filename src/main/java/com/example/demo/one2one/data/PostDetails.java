package com.example.demo.one2one.data;

import javax.persistence.*;
import java.util.Date;


/**
 * PostDetails is the Child.
 * here demo to implement with "foreign key" in another table
 */
@Entity
public class PostDetails {

    public PostDetails() {
    }

    public PostDetails(Long id, boolean visible) {
        this.id = id;
        this.visible = visible;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = new Date();

    private boolean visible;

    public Post getPost() {
        return post;
    }

    @OneToOne(mappedBy = "details")  // "mappedBy" means the "foreign key" is in another table
    private Post post;

    public Long getId() {
        return id;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setPost(Post post) {
        this.post = post;
    }


    @Override
    public String toString() {
        return "PostDetails{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", visible=" + visible +
                ", post=" + post.getId() +
                '}';
    }
}
