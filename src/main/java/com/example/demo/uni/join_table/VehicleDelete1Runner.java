package com.example.demo.uni.join_table;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * delete existing Vehicle (@ManyToOne and @JoinTable) belonging to Person
 *
 * will need 2 SQL:
 *
 * delete from vehicle_person where vehicle_id=?
 * delete from vehicle where id=?
 *
 */

@Slf4j
@Order(405)
@Component("delete-1-Vehicle")
public class VehicleDelete1Runner implements CommandLineRunner {

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverStore driverStore;

    @Autowired
    private VehicleStore vehicleStore;


    @Override
    public void run(String... args) throws Exception {
        log.info("=========== delete 1 Vehicle begin ==========");

        //Optional<Person> person = personStore.findByName("Mark");
        Optional<Vehicle> BMW6 = vehicleStore.findByName("BMW 6");
        if(BMW6.isPresent()) {
            driverService.deleteVehicle(BMW6.get());
        }

        log.info("=========== delete 1 Vehicle end ==========");
        driverService.queryPerson().forEach(e-> log.info("{}", e.toString()));
        driverService.queryVehicle().forEach(e-> log.info("{}", e.toString()));
        log.info("=========== query Vehicle end ==========");
    }
}
