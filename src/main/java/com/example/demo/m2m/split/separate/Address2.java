package com.example.demo.m2m.split.separate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Address2 implements Serializable {
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
    private List<Person2Address2> associations = new ArrayList<>();

    public Address2() {
    }

    public Address2(String street, String number, String postalCode) {
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
        Address2 address = (Address2) o;
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



    public void addPerson(Person2 person) {
        Person2Address2 personAddress = new Person2Address2(person,this);
        this.associations.add(personAddress);
        person.getAssociations().add(personAddress);
    }

    public void removePerson(Person2 person) {
        Person2Address2 personAddress = new Person2Address2(person, this);
//        address.getOwners().remove(personAddress);
//        addresses.remove(personAddress);
//        personAddress.setPerson(null);
//        personAddress.setAddress(null);
    }

    @Override
    public String toString() {
        return "Address2{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

    public List<Person2Address2> getAssociations() {
        return associations;
    }

    public void setAssociations(List<Person2Address2> associations) {
        this.associations = associations;
    }
}
