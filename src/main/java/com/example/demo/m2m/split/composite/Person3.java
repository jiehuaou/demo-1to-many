package com.example.demo.m2m.split.composite;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * split many-to-many => 2 one-to-many with Composite Key
 *
 * Person, Address and JoinEntity (Composite Key)  (Owner side)
 *
 */
@Entity
public class Person3 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String registrationNumber;

    @OneToMany(
            mappedBy = "person", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    private List<Person3Address3> associations = new ArrayList<>();

    public Person3() {
    }

    public Person3(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    //Getters and setters are omitted for brevity

    public void addAddress(Address3 address) {
        Person3Address3 personAddress = new Person3Address3(this, address);
        associations.add(personAddress);
        address.getAssociations().add(personAddress);
    }

    public void removeAddress(Address3 address) {
        Person3Address3 personAddress = new Person3Address3(this, address);
        address.getAssociations().remove(personAddress);
        associations.remove(personAddress);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person3 person = (Person3) o;
        return registrationNumber!=null && registrationNumber.equals(person.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }

    @Override
    public String toString() {
        return "Person3 {" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", associations=" + joinAddress() +
                '}';
    }

    public String toShortString() {
        return "Person3 {" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                '}';
    }

    private List<Address3> joinAddress() {
        return this.associations.stream()
                .map(e->e.getAddress())
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public List<Person3Address3> getAssociations() {
        return associations;
    }

    public void setAssociations(List<Person3Address3> associations) {
        this.associations = associations;
    }
}
