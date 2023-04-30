package com.example.demo.m2m.split.composite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Person3Address3Service {
    @Autowired
    private Person3Store person3Store;
    @Autowired
    private Address3Store addressStore;

    @Transactional(propagation = Propagation.REQUIRED)
    public Person3 savePerson(Person3 person) {
        return person3Store.save(person);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Address3 saveAddress(Address3 address) {
        return addressStore.save(address);
    }
}
