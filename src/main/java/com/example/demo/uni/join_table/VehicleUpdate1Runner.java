package com.example.demo.uni.join_table;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * create new Vehicle with existing Person
 */

@Slf4j
@Order(402)
@Component("update-1-Vehicle")
public class VehicleUpdate1Runner implements CommandLineRunner {

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverStore driverStore;

    @Autowired
    private VehicleStore vehicleStore;


    @Override
    public void run(String... args) throws Exception {
        log.info("=========== update 1 Vehicle begin ==========");

        driverStore.findByName("Joe").ifPresent(person -> {
            Vehicle toyota = new Vehicle("Toyota", person);
            driverService.saveVehicle(toyota);
        });

        log.info("=========== update 1 Vehicle end ==========");
        driverService.queryPerson().forEach(e-> log.info("{}", e.toString()));
        driverService.queryVehicle().forEach(e-> log.info("{}", e.toString()));
        log.info("=========== query Vehicle end ==========");
    }
}
