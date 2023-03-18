# demo-n-to-many

demo JPA, Hibernate, 1-to-many and more

# Entity Lifecycle Model in JPA & Hibernate

![JPA info](./images/entity-lifecycle.png "lifecycle-diagram")

**Transient**
The lifecycle state of a newly instantiated entity object is called transient. The entity hasn’t been persisted yet, so it doesn’t represent any database record.

**Managed**
All entity objects attached to the current persistence context are in the lifecycle state managed.

**Detached**
An entity that was previously managed but is no longer attached to the current persistence context is in the lifecycle state detached.

**Removed**
When you call the remove method on your EntityManager, the mapped database record doesn’t get removed immediately. The entity object only changes its lifecycle state to removed.

# Overview of JPA/Hibernate Cascade

# GenerationType  IDENTITY vs SEQUENCE vs AUTO

## AUTO Generation

If we're using the default generation type, the persistence provider will determine values based on the type of the primary key attribute. This type can be numerical or UUID.

For numeric values, the generation is based on a **Sequence or Table** generator, 
```java
 @Id
    @GeneratedValue
    private long studentId;
```
while UUID values will use the **UUID** Generator.
```java
 @Id
    @GeneratedValue
    private UUID studentId;
```

## IDENTITY Generation

**IDENTITY** sequencing uses **special IDENTITY columns** in the database to allow the database to automatically assign an id to the object when its row is inserted. Identity columns are supported in many databases, such as MySQL, DB2, SQL Server, Sybase and Postgres. Oracle does not support IDENTITY columns but they can be simulated through using sequence objects and triggers.

## SEQUENCE Generation

This generator uses **SEQUENCE object** if our database supports them. It switches to table generation if they aren't supported.

In order to customize the sequence name, we can use the @GenericGenerator annotation with SequenceStyleGenerator strategy.

# JPA One To Many example

![JPA info](./images/jpa-one-to-many-diagram.png "jpa-one-to-many-diagram")

# JPA Many To Many example

![JPA info](./images/jpa-many-to-many-diagram.png "jpa-many-to-many-diagram")


@ManyToMany annotation is used for Many-to-Many association between two entities: Tutorial and Tag.

Every Many-to-Many relationship has two sides:

* the owning side
* the non-owning (inverse side)

In this demo, Tutorial entity is the owner of the relationship and Tag entity is the inverse side.

*The **join table** is specified on the **owning side** (Tutorial) using **@JoinTable** annotation. This relationship is bidirectional, the **inverse side** (Tag) must use the **mappedBy** element to specify the **relationship field or property** of the **owning side**.*

So, the side which doesn’t have the **mappedBy** attribute is the owner, the side which has the **mappedBy** attribute is the inverse side.

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

# JPA one-to-one 

implement solution

* Implementing With a Shared Primary Key, Child Table copy PK from Parent
* impl with a Foreign Key, Parent Table owns the foreign key column
* Implementing With a Join Table 

Some Notes

* **@PrimaryKeyJoinColumn** , PrimaryKey work as JoinColumn
* **@JoinColumn(name = "post_id")** , means the foreign key column is defined in this Table
* **@MapsId**, means PK values will be copied from the Parent entity
* @OneToOne(**mappedBy = "details"**) , means the "foreign key" is in another table


# "Detached entity passed to persist" solution

1. only use CascadeType.MERGE instead of CascadeType.PERSIST or CascadeType.ALL

# Bidirectional parent-child associations

The most common & the most efficient approach is when the **@ManyToOne** side controls the association 
and the **@OneToMany** end is using the “**mappedBy**” option.

# Set Is Better Than List in @ManyToMany, @OneToMany

**collection is List**

when remove a child branch1, Hibernate delete all branch then re-insert branch2 and branch3 
```java

@Entity
public class Company {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Branch> branchList = new ArrayList<>();
}
```

**collection is Set**

when remove a child branch1, Hibernate only issue single delete sql "delete * from branch where id=?"
```java

@Entity
public class Company {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Branch> branchList = new HashSet<>();
}
```

**Preserving the Order of the ResultSet**

* Use @OrderBy to ask the database to order the fetched data by the given columns,
* Use @OrderColumn to permanently order this via an extra column

Behind the scenes, Hibernate will preserve the order via a **LinkedHashSet**.

```java
@Entity
public class Company {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("name DESC")
    private Set<Branch> branchList = new HashSet<>();
}
```