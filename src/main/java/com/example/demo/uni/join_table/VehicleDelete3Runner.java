package com.example.demo.uni.join_table;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * delete existing Person
 *
 * we must code to delete associated vehicle first .
 */

@Slf4j
@Order(407)
@Component("delete-3-Vehicle")
public class VehicleDelete3Runner implements CommandLineRunner {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonStore personStore;

    @Autowired
    private VehicleStore vehicleStore;


    @Override
    public void run(String... args) throws Exception {
        log.info("=========== delete 3 Person begin ==========");

        Optional<Person> person = personStore.findByName("Mark");
        if(person.isPresent()) {
            /**
             * internally
             * we must code to delete associated vehicle (@ManyToOne) first,
             * then delete Person.
             */
            personService.deletePerson(person.get());
        }

        log.info("=========== delete 3 Person end ==========");
        personService.queryPerson().forEach(e-> log.info("{}", e.toString()));
        personService.queryVehicle().forEach(e-> log.info("{}", e.toString()));
        log.info("=========== query Vehicle end ==========");
    }
}
