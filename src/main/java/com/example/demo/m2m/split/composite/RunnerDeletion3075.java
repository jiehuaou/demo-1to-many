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
 * demo deletion: Person, Address and JoinEntity (Composite Key)  (Owner side)
 *
 */
@Slf4j
@Order(3075)
@Component("RunnerDeletion3075")
public class RunnerDeletion3075 implements CommandLineRunner {

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
        log.info("================== Runner JoinEntity delete 3075 begin ==================");

        Person3 p1 = person3Store.findByRegistrationNumber("x001");
        service.deletePerson(p1);

        Address3 a3 = address3Store.findByNumber("567");
        service.deleteAddress(a3);

        log.info("================== Runner JoinEntity delete 3075 end ==================");
        person3Store.findAll().stream().forEach(e->log.info("{}", e));
        log.info("================== Runner JoinEntity delete 3075 checking end ==================");
        address3Store.findAll().stream().forEach(e->log.info("{}", e.toLongString()));
        log.info("================== Runner JoinEntity delete 3075 checking end ==================");

    }

}
