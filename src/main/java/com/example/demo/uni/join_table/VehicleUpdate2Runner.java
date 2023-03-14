package com.example.demo.uni.join_table;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * update existing Vehicle with another existing Person
 */

@Slf4j
@Order(403)
@Component("update-2-Vehicle")
public class VehicleUpdate2Runner implements CommandLineRunner {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonStore personStore;

    @Autowired
    private VehicleStore vehicleStore;


    @Override
    public void run(String... args) throws Exception {
        log.info("=========== update 1 Vehicle begin ==========");

        Optional<Person> person = personStore.findByName("Mark");
        Optional<Vehicle> toyota = vehicleStore.findByName("Toyota");
        if(toyota.isPresent() && person.isPresent()) {
            toyota.get().setPerson(person.get());
            personService.saveVehicle(toyota.get());
        }

        log.info("=========== update 1 Vehicle end ==========");
        personService.queryPerson().forEach(e-> log.info("{}", e.toString()));
        personService.queryVehicle().forEach(e-> log.info("{}", e.toString()));
        log.info("=========== query Vehicle end ==========");
    }
}
