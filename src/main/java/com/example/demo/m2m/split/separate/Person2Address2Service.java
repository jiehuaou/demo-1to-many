package com.example.demo.m2m.split.separate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Person2Address2Service {
    @Autowired
    private Person2Store personStore;
    @Autowired
    private Address2Store addressStore;

    @Transactional(propagation = Propagation.REQUIRED)
    public Person2 savePerson(Person2 person) {
        return personStore.save(person);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Address2 saveAddress(Address2 address) {
        return addressStore.save(address);
    }
}
