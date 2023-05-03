package com.example.demo.m2m.split.composite;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class Person3Address3Service {
    @Autowired
    private Person3Store person3Store;
    @Autowired
    private Address3Store address3Store;

    @Transactional(propagation = Propagation.REQUIRED)
    public Person3 savePerson(Person3 person) {
        return person3Store.save(person);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void mapNewPersonWithAddress(Person3 p1, String addressNumber) {
//        Person3 p1 = new Person3(regNumber);
        Address3 a1 = address3Store.findByNumber(addressNumber);
        p1.addAddress(a1);
        person3Store.save(p1);
        log.info("======== Transaction End ========== ");
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void mapNewAddressWithPerson(Address3 a3, String registrationNumber) {
        Person3 p1 = person3Store.findByRegistrationNumber(registrationNumber);
        a3.addPerson(p1);
        address3Store.save(a3);
        log.info("======== Transaction End ========== ");
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public Address3 saveAddress(Address3 address) {
        return address3Store.save(address);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletePerson(Person3 person) {
        person3Store.delete(person);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAddress(Address3 address) {
        address3Store.delete(address);
    }


}
