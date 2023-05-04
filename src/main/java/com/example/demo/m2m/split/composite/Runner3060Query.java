package com.example.demo.m2m.split.composite;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * split many-to-many => 2 one-to-many with Composite Key
 *
 * demo query: Person, Address and JoinEntity (Composite Key)  (Owner side)
 *
 */
@Slf4j
@Order(3060)
@Component("RunnerQuery3060")
public class Runner3060Query implements CommandLineRunner {

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
        log.info("================== Runner JoinEntity Query 3060 begin ==================");

        person3Store.findByAssociations_PersonAddressKey_PersonId(3L)
                .stream().forEach(e->log.info("{}", e));


        person3Store.findByAssociations_Address_Street("street-def")
                .stream().forEach(e->log.info("{}", e));

        //Address3 a3 = address3Store.findByNumber("567");


        log.info("================== Runner JoinEntity Query 3060 end ==================");
        person3Store.findAll().stream().forEach(e->log.info("{}", e));
        log.info("================== Runner JoinEntity Query 3060 checking end ==================");
        address3Store.findAll().stream().forEach(e->log.info("{}", e.toLongString()));
        log.info("================== Runner JoinEntity Query 3060 checking end ==================");

    }

}
