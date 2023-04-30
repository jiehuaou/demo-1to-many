package com.example.demo.m2m.split.separate;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity()
public class Person2 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String registrationNumber;

    @OneToMany(
            mappedBy = "person",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    private List<Person2Address2> associations = new ArrayList<>();

    public Person2() {
    }

    public Person2(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    //Getters and setters are omitted for brevity

    public void addAddress(Address2 address) {
        Person2Address2 personAddress = new Person2Address2(this, address);
        this.associations.add(personAddress);
        address.getAssociations().add(personAddress);
    }

    public void removeAddress(Address2 address) {
        Objects.requireNonNull(address);
        Objects.requireNonNull(address.getId());
        Person2Address2 personAddress = new Person2Address2(this, address);
        this.associations.remove(personAddress);
        address.getAssociations().remove(personAddress);
        personAddress.setPerson(null);
        personAddress.setAddress(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person2 person = (Person2) o;
        return registrationNumber!=null && registrationNumber.equals(person.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }

    @Override
    public String toString() {
        return "Person2{" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", associations=" + joinAddress() +
                '}';
    }

    private List<Address2> joinAddress() {
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

    public List<Person2Address2> getAssociations() {
        return associations;
    }

    public void setAssociations(List<Person2Address2> associations) {
        this.associations = associations;
    }
}
