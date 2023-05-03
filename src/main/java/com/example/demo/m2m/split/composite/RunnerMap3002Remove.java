package com.example.demo.m2m.split.composite;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

/**
 * split many-to-many => 2 one-to-many with Composite Key
 *
 * demo un-map: Person, Address and JoinEntity (Composite Key)  (Owner side)
 *
 */
@Slf4j
@Order(3002)
@Component("RunnerMap3002Remove")
public class RunnerMap3002Remove implements CommandLineRunner {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private Person3Store person3Store;
    @Autowired
    private Address3Store address3Store;
    @Autowired
    private Person3Address3Service service;

    @Override
    public void run(String... args) throws Exception {
        log.info("================== Runner JoinEntity UnMap 3002 begin ==================");

        removePerson();
        removeAddress();

        log.info("================== Runner JoinEntity UnMap 3002 end ==================");
        person3Store.findAll().stream().forEach(e->log.info("{}", e));
        log.info("================== Runner JoinEntity UnMap 3002 checking end ==================");

        recoverPerson();
        recoverAddress();

    }

    void removeAddress() {
        Person3 p1 = person3Store.findByRegistrationNumber("x001");
        Address3 a1 = address3Store.findByNumber("123");
        p1.removeAddress(a1);
        service.savePerson(p1);
    }

    void removePerson() {
        Address3 a3 = address3Store.findByNumber("567");
        Person3 p2 = person3Store.findByRegistrationNumber("x002");
        a3.removePerson(p2);
        service.saveAddress(a3);
    }
    void recoverAddress() {
        Person3 p1 = person3Store.findByRegistrationNumber("x001");
        Address3 a1 = address3Store.findByNumber("123");
        p1.addAddress(a1);
        service.savePerson(p1);
    }

    void recoverPerson() {
        Address3 a3 = address3Store.findByNumber("567");
        Person3 p2 = person3Store.findByRegistrationNumber("x002");
        a3.addPerson(p2);
        service.saveAddress(a3);
    }
}
