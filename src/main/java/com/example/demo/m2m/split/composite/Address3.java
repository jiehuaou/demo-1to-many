package com.example.demo.m2m.split.composite;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Address3 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    @Column(name = "streetNo")
    private String number;

    private String postalCode;

    @OneToMany(
            mappedBy = "address",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    private List<Person3Address3> persons = new ArrayList<>();

    public Address3() {
    }

    public Address3(String street, String number, String postalCode) {
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
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
        Address3 address = (Address3) o;
        return getId()!=null && getId().equals(address.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }



    public void addPerson(Person3 person) {
        Person3Address3 personAddress = new Person3Address3(person,this);
        persons.add(personAddress);
        person.getAddresses().add(personAddress);
    }

    public void removePerson(Person3 person) {
     //   PersonAddress personAddress = new PersonAddress(person, this);
//        address.getOwners().remove(personAddress);
//        addresses.remove(personAddress);
//        personAddress.setPerson(null);
//        personAddress.setAddress(null);
    }

    @Override
    public String toString() {
        return "Address3{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

    public List<Person3Address3> getPersons() {
        return persons;
    }

    public void setPersons(List<Person3Address3> persons) {
        this.persons = persons;
    }
}
