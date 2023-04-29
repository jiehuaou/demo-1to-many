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
    private DriverService driverService;

    @Autowired
    private DriverStore driverStore;

    @Autowired
    private VehicleStore vehicleStore;


    @Override
    public void run(String... args) throws Exception {
        log.info("=========== update 1 Vehicle begin ==========");

        Optional<Driver> person = driverStore.findByName("Mark");
        Optional<Vehicle> toyota = vehicleStore.findByName("Toyota");
        if(toyota.isPresent() && person.isPresent()) {
            toyota.get().setDriver(person.get());
            driverService.saveVehicle(toyota.get());
        }

        log.info("=========== update 1 Vehicle end ==========");
        driverService.queryPerson().forEach(e-> log.info("{}", e.toString()));
        driverService.queryVehicle().forEach(e-> log.info("{}", e.toString()));
        log.info("=========== query Vehicle end ==========");
    }
}
