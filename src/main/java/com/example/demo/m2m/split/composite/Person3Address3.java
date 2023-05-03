package com.example.demo.m2m.split.composite;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * split many-to-many => 2 one-to-many with Composite Key
 *
 * Person, Address and JoinEntity (Composite Key)  (Owner side)
 */
@Entity
public class Person3Address3 implements Serializable {

    @EmbeddedId
    private Person3Address3Key personAddressKey;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private Person3 person;


    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("addressId")
    @JoinColumn(name = "address_id")
    private Address3 address;

    public Person3Address3() {
    }

    public Person3Address3(Person3 person, Address3 address) {
        this.personAddressKey = new Person3Address3Key(person.getId(), address.getId());
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
        Person3Address3 that = (Person3Address3) o;
        return person.getId().equals(that.person.getId()) &&
                address.getId().equals(that.address.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }

    public Person3 getPerson() {
        return person;
    }

    public void setPerson(Person3 person) {
        this.person = person;
    }

    public Address3 getAddress() {
        return address;
    }

    public void setAddress(Address3 address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person3Address3{" +
                "person=" + Optional.ofNullable(person).map(p->p.getId().toString()).orElseGet(()->"unknown") +
                ", address=" + Optional.ofNullable(address).map(p->p.getId().toString()).orElseGet(()->"unknown") +
                '}';
    }

}
