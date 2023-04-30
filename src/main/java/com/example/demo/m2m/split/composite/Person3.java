package com.example.demo.m2m.split.composite;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity()
public class Person3 implements Serializable {
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
    private List<Person3Address3> addresses = new ArrayList<>();

    public Person3() {
    }

    public Person3(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    //Getters and setters are omitted for brevity

    public void addAddress(Address3 address) {
//        PersonAddress personAddress = new PersonAddress(this, address);
//        //addresses.stream().filter(e->e.)
//        addresses.add(personAddress);
//        address.getPersons().add(personAddress);
    }

    public void removeAddress(Address3 address) {
//        PersonAddress personAddress = new PersonAddress(this, address);
//        address.getPersons().remove(personAddress);
//        addresses.remove(personAddress);
//        personAddress.setPerson(null);
//        personAddress.setAddress(null);
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
        return "Person{" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", addresses=" + joinAddress() +
                '}';
    }

    private List<Address3> joinAddress() {
        return this.addresses.stream()
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

    public List<Person3Address3> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Person3Address3> addresses) {
        this.addresses = addresses;
    }
}
