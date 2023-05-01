package com.example.demo.m2m.split.separate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * split many-to-many => 2 one-to-many with JoinEntity
 *
 * Person, Address and JoinEntity (Owner side)
 *
 */

@Entity @Getter @Setter
public class Person2Address2 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Person2 person;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address2 address;

    public Person2Address2() {
    }

    public Person2Address2(Person2 person, Address2 address) {
        this.person = person;
        this.address = address;
    }

    //Getters and setters are omitted for brevity

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
//        if (person==null || address==null || person.getId()==null || address.getId()==null) {
//            return false;
//        }
        Person2Address2 that = (Person2Address2) o;
        return getId()!=null && getId().equals(that.getId());
//        return Objects.equals(person.getId(), that.person.getId()) &&
//                Objects.equals(address.getId(), that.address.getId());
    }

    public boolean equalValue(Person2Address2 o) {
        if (this == o) {
            return true;
        }
        if (person==null || address==null || o.person==null || o.address==null) {
            return false;
        }
        return Objects.equals(person.getId(), o.person.getId()) &&
                Objects.equals(address.getId(), o.address.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }



    @Override
    public String toString() {
        return "Person2Address2{" +
                "person=" + Optional.ofNullable(person).map(p->p.getId().toString()).orElseGet(()->"unknown") +
                ", address=" + Optional.ofNullable(address).map(p->p.getId().toString()).orElseGet(()->"unknown") +
                '}';
    }

}
