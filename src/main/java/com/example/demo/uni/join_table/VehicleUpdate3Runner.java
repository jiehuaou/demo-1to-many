package com.example.demo.uni.join_table;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * remove relation from existing Vehicle
 */

@Slf4j
@Order(404)
@Component("update-3-Vehicle")
public class VehicleUpdate3Runner implements CommandLineRunner {

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverStore driverStore;

    @Autowired
    private VehicleStore vehicleStore;


    @Override
    public void run(String... args) throws Exception {
        log.info("=========== update 1 Vehicle begin ==========");

        //Optional<Person> person = personStore.findByName("Mark");
        Optional<Vehicle> toyota = vehicleStore.findByName("Toyota");
        if(toyota.isPresent()) {
            toyota.get().setDriver(null);  // remove relation between Vehicle "Toyota" and Person "Mark"
            driverService.saveVehicle(toyota.get());  // SQL : delete from vehicle_person where vehicle_id=?
        }

        log.info("=========== update 1 Vehicle end ==========");
        driverService.queryPerson().forEach(e-> log.info("{}", e.toString()));
        driverService.queryVehicle().forEach(e-> log.info("{}", e.toString()));
        log.info("=========== query Vehicle end ==========");
    }
}
