package com.example.demo.m2m.split.separate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

@Entity
public class Person2Address2 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
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
        if (person==null || address==null || o == null || getClass() != o.getClass()) {
            return false;
        }
        if (person.getId()==null || address.getId()==null) {
            return false;
        }
        Person2Address2 that = (Person2Address2) o;
        return Objects.equals(person.getId(), that.person.getId()) &&
                Objects.equals(address.getId(), that.address.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }

    public Person2 getPerson() {
        return person;
    }

    public void setPerson(Person2 person) {
        this.person = person;
    }

    public Address2 getAddress() {
        return address;
    }

    public void setAddress(Address2 address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person2Address2{" +
                "person=" + Optional.ofNullable(person).map(p->p.getId().toString()).orElseGet(()->"unknown") +
                ", address=" + Optional.ofNullable(address).map(p->p.getId().toString()).orElseGet(()->"unknown") +
                '}';
    }

}
