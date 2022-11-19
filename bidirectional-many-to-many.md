# MappedBy in bi-directional @ManyToMany : what is the reason

1. What is the reason for setting MappedBy in bidirectional many-to-many relationships?
2. When one table has significant amount of records, while other has a few, which side is better to put mappedBy?

## Answers

If you want to prevent both sides (in a bidirectional relationship) from having join tables, 
a good idea, then you need to have a **mappedBy=** element on one side.

Whether or not there is a join table is controlled by the mappedBy="name" element of the 
@ManyToMany annotation. The Javadoc for mappedBy for the ManyToMany annotation says:

> The field that owns the relationship. Required unless the relationship is unidirectional.

For your (bidirectional) example, if there were only two @ManyToMany annotations and no 
mappedBy= element, the default will have two Entity tables and two Join Tables:

```
Hibernate: create table SideA (id bigint not null, primary key (id))
Hibernate: create table SideA_SideB (sidea_id bigint not null, sidebs_id bigint not null, primary key (sidea_id, sidebs_id))
Hibernate: create table SideB (id bigint not null, primary key (id))
Hibernate: create table SideB_SideA (sideb_id bigint not null, sideas_id bigint not null, primary key (sideb_id, sideas_id))
```

While this is saying that each Entity "owns" its ManyToMany relationship, the extra join 
table is redundant in the typical use case, and the Javadoc says you need a mappedBy annotation. 
If I decide to have SideA "own" the relationship, then I add the mappedBy= element to the SideB 
entity to specify that it doesn't own the relationship:

```java

@Entity
public class SideA {
    @ManyToMany
    Set<SideB> sidebs;   //<-- this is the Owner side
}
@Entity
public class SideB {
    @ManyToMany(mappedBy="sidebs")
    Set<SideA> sideas;
}
```

Since the SideB entity no longer owns its ManyToMany relationship, the extra JoinTable will not be created:

```shell
Hibernate: create table SideA (id bigint not null, primary key (id))
Hibernate: create table SideB (id bigint not null, primary key (id))
Hibernate: create table SideA_SideB (sideas_id bigint not null, sidebs_id bigint not null, primary key (sideas_id, sidebs_id))
```

This is important to the developer because he or she must understand that **no relationship is persisted 
unless it's added to the owning entity, in this case the SideA entity**.

So, if you have a bidirectional ManyToMany relationship, which means you have ManyToMany on both entities 
involved, then you should add a mappedBy="name" on one of them as per the Javadoc and to avoid having a 
redundant join table.

As to which side to make the owning entity, there is no correct answer, it depends on what your system 
thinks is best. The relationship will only be persisted when entries are put in the owning side so 
you have to ask yourself whether you more commonly change a SideA's list or SideB's list. **If SideA 
owns the relationship then you update the relationship by adding or removing SideB instances from a 
SideA instance** . but if you had a list of SideA instances for a SideB that you wanted to persist, you 
would need to iterate through the list and alter each instance of SideA in the list.

