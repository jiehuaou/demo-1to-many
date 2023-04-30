package com.example.demo.m2m.split.composite;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

/**
 * demo : create Person and Address ( many-to-many => 2 one-to-many )
 *
 */
@Slf4j
@Order(3001)
@Component("RunnerCreation3001")
public class RunnerCreation3001 implements CommandLineRunner {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private Person3Store person3Store;
    @Autowired
    private Person3Address3Service service;

    @Override
    public void run(String... args) throws Exception {
        log.info("================== Runner Creation 2001 begin ==================");

        Person3 p1 = new Person3("x001");
        Address3 a1 = new Address3("street-abc", "123", "code123");
        Address3 a2 = new Address3("street-eee", "333", "code333");

        p1.addAddress(a1);
        p1.addAddress(a2);
        service.savePerson(p1);

        Address3 a3 = new Address3("street-def", "567", "code567");
        Person3 p2 = new Person3("x002");
        Person3 p3 = new Person3("x003");



        log.info("================== Runner Creation 2001 end ==================");
        person3Store.findAll().stream().forEach(e->log.info("{}", e));
        log.info("================== Runner Creation 2001 checking end ==================");
    }
}
