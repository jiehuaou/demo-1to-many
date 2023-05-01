package com.example.demo.m2m.split.separate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

/**
 *  many-to-many => 2 one-to-many with JoinEntity
 *
 * demo : delete Person and association as well
 *
 */
@Slf4j
@Order(2005)
@Component("RunnerDeletion2005")
public class RunnerDeletion2005 implements CommandLineRunner {

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

        Person2 p1 = personStore.findByRegistrationNumber("x001");
        service.deletePerson(p1);


        log.info("================== Runner Creation 2001 end ==================");
        personStore.findAll().stream().forEach(e->log.info("{}", e));
        log.info("================== Runner Creation 2001 checking end ==================");
    }
}
