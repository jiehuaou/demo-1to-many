package com.example.demo.one2one.data;

import javax.persistence.*;

/**
 * the Owner is the Child.
 */
@Entity
public class Owner {
    @Id
    @Column(name = "post_id")
    private Long id;

    public Owner(){}

    public Owner(Long id, String email, String mobile) {
        this.id = id;
        this.email = email;
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private String email;

    private String mobile;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @OneToOne()
    @MapsId      // indicates that the primary key values will be copied from the User entity
    @JoinColumn(name = "post_id") // this table owns the foreign key column
    private Post post;

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
