# demo-1to-many
demo JPA, Hibernate, 1-to-many and more

# JPA One To Many example

![JPA info](./images/jpa-one-to-many-diagram.png "jpa-one-to-many-diagram")

# JPA Many To Many example

![JPA info](./images/jpa-many-to-many-diagram.png "jpa-many-to-many-diagram")


@ManyToMany annotation is used for Many-to-Many association between two entities: Tutorial and Tag.

Every Many-to-Many relationship has two sides:

* the owning side
* the non-owning (inverse side)

In this demo, Tutorial entity is the owner of the relationship and Tag entity is the inverse side.

The join table is specified on the owning side (Tutorial) using @JoinTable annotation. This relationship is bidirectional, the inverse side (Tag) must use the mappedBy element to specify the relationship field or property of the owning side.

So, the side which doesn’t have the **mappedBy** attribute is the owner, the side which has the mappedBy attribute is the inverse side.

The owner side is the side which Hibernate looks at to know which association exists. For example, if you add a Tag in the set of tags of a Tutorial, a new row will be inserted by Hibernate in the join table (tutorial_tags). On the contrary, if you add a Tutorial to the set of tutorials of a Tag, nothing will be modified in the database.

@JsonIgnore is used to ignore the logical property used in serialization and deserialization

```java
@Entity
@Table(name = "tutorials")
public class Tutorial {
    @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      })
    @JoinTable(name = "tutorial_tags",
        joinColumns = { @JoinColumn(name = "tutorial_id") },
        inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<Tag> tags = new HashSet<>();
}

@Entity
@Table(name = "tags")
public class Tag {
    @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      mappedBy = "tags")
    @JsonIgnore
    private Set<Tutorial> tutorials = new HashSet<>();
}
```
