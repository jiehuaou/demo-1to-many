package com.example.demo.m2m.split.separate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

/**
 *  many-to-many => 2 one-to-many with JoinEntity
 * demo : remove association between Person and Address
 *
 */
@Slf4j
@Order(2002)
@Component("RunnerUnmap2002")
public class RunnerUnmap2002 implements CommandLineRunner {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private Person2Store personStore;
    @Autowired
    private Address2Store address2Store;
    @Autowired
    private Person2Address2Service service;

    @Override
    public void run(String... args) throws Exception {
        log.info("================== Runner Creation 2001 begin ==================");

//        Person2 p1 = new Person2("x001");
//        Address2 a1 = new Address2("street-abc", "123", "code123");
//        Address2 a2 = new Address2("street-eee", "333", "code333");
//        p1 = service.savePerson(p1);
//        a1 = service.saveAddress(a1);
//        a2 = service.saveAddress(a2);

        Person2 p1 = personStore.findByRegistrationNumber("x001");
        Address2 a1 = address2Store.findByNumber("123");
        p1.removeAddress(a1);

        p1 = service.savePerson(p1);


        log.info("================== Runner Creation 2001 end ==================");
        personStore.findAll().stream().forEach(e->log.info("{}", e));
        log.info("================== Runner Creation 2001 checking end ==================");

       recoverAddress();
    }

    void recoverAddress() {
        Person2 p1 = personStore.findByRegistrationNumber("x001");
        Address2 a1 = address2Store.findByNumber("123");
        p1.addAddress(a1);
        service.savePerson(p1);
    }
}
