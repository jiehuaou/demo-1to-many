package com.example.demo.m2m.split.composite;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * split many-to-many => 2 one-to-many with Composite Key
 *
 * demo map new Entity (Person) with existing Entity (Address) :
 *
 *
 */
@Slf4j
@Order(3003)
@Component("RunnerMap3003NewOld")
public class RunnerMap3003NewOld implements CommandLineRunner {

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
        log.info("================== Runner JoinEntity 3003 New & Old begin ==================");

        map_new_person_with_existing_address();
        map_new_address_with_existing_person();

        log.info("================== Runner JoinEntity 3003 New & Old end ==================");
        person3Store.findAll().stream().forEach(e->log.info("{}", e));
        log.info("================== Runner JoinEntity 3003 New & Old checking end ==================");
        address3Store.findAll().stream().forEach(e->log.info("{}", e.toLongString()));
        log.info("================== Runner JoinEntity ++++++++++++++ checking end ==================");

    }


    private void map_new_address_with_existing_person() {
        Address3 a3 = new Address3("street-999", "999", "code-999");
        service.mapNewAddressWithPerson(a3, "x002");
        log.info("================== ");
    }


    public void map_new_person_with_existing_address() {
        Person3 p1 = new Person3("x-new-person-999");
        service.mapNewPersonWithAddress(p1, "123");
        log.info("================== ");

    }


}
