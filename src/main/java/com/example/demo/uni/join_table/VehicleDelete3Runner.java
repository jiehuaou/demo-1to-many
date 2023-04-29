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
    private DriverService driverService;

    @Autowired
    private DriverStore driverStore;

    @Autowired
    private VehicleStore vehicleStore;


    @Override
    public void run(String... args) throws Exception {
        log.info("=========== delete 3 Person begin ==========");

        Optional<Driver> person = driverStore.findByName("Mark");
        if(person.isPresent()) {
            /**
             * internally
             * we must code to delete associated vehicle (@ManyToOne) first,
             * then delete Person.
             */
            driverService.deletePerson(person.get());
        }

        log.info("=========== delete 3 Person end ==========");
        driverService.queryPerson().forEach(e-> log.info("{}", e.toString()));
        driverService.queryVehicle().forEach(e-> log.info("{}", e.toString()));
        log.info("=========== query Vehicle end ==========");
    }
}
