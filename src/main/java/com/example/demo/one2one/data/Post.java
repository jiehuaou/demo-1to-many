package com.example.demo.one2one.data;

import com.example.demo.one2one.svc.PostEeventListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The Post entity plays the Parent role,
 * the PostDetails is the Child role,
 * the Owner is the Child role.
 */
@EntityListeners(PostEeventListener.class)
@Entity
public class Post {

    public Post() {
    }

    public Post(Long id, Long version, String name, Owner owner, PostDetails details, Category category) {
        this.version = version;
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.details = details;
        this.category = category;
        owner.setPost(this);
        details.setPost(this);
        category.setPost(this);

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

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL)  // "mappedBy" means the "foreign key" is in another table
    @PrimaryKeyJoinColumn   // Implementing With a Shared Primary Key
    private Owner owner;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "detail_id", referencedColumnName = "id")  // impl with a Foreign Key, owns the foreign key column
    private PostDetails details;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "post_cat",
            joinColumns = { @JoinColumn(name = "post_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "cat_id", referencedColumnName = "id") })
    private Category category;

    public LocalDateTime getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(LocalDateTime loadTime) {
        this.loadTime = loadTime;
    }

    @Transient
    private LocalDateTime loadTime;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category catagory) {
        this.category = catagory;
        catagory.setPost(this);
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
        owner.setPost(this);
    }




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
                "version=" + version + "\n" +
                ", id=" + id + "\n" +
                ", name='" + name + "\'\n" +
                ", owner='" + owner + "\'\n" +
                ", loadTime='" + loadTime + "\'\n" +
                ", details=" + details + "\n" +
                ", category=" + category + "\n" +
                '}';
    }
}
