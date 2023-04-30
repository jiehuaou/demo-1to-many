package com.example.demo.m2m.split.separate;

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
@Order(-1)
@Component("RunnerCreation2001")
public class RunnerCreation2001 implements CommandLineRunner {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private Person2Store personStore;
    @Autowired
    private Person2Address2Service service;

    @Override
    public void run(String... args) throws Exception {
        log.info("================== Runner Creation 2001 begin ==================");

        Person2 p1 = new Person2("x001");
        Address2 a1 = new Address2("street-abc", "123", "code123");
        Address2 a2 = new Address2("street-eee", "333", "code333");

        p1.addAddress(a1);
        p1.addAddress(a2);
        service.savePerson(p1);

        Address2 a3 = new Address2("street-def", "567", "code567");
        Person2 p2 = new Person2("x002");
        Person2 p3 = new Person2("x003");
        a3.addPerson(p2);
        a3.addPerson(p3);
        service.saveAddress(a3);


        log.info("================== Runner Creation 2001 end ==================");
        personStore.findAll().stream().forEach(e->log.info("{}", e));
        log.info("================== Runner Creation 2001 checking end ==================");
    }
}
