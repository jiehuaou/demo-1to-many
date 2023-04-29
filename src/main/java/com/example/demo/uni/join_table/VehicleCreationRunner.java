package com.example.demo.uni.join_table;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * create new Vehicle with new Person
 * we must save Person first then save Vehicle ( @ManyToOne).
 */
@Slf4j
@Order(401)
@Component("create-Vehicle")
public class VehicleCreationRunner implements CommandLineRunner {

    @Autowired
    private DriverService driverService;


    @Override
    public void run(String... args) throws Exception {
        log.info("=========== create Vehicle begin ==========");
        Driver driver1 = new Driver("Joe");
        driverService.savePerson(driver1);

        Vehicle bmw5 = new Vehicle("BMW 5", driver1);
        Vehicle model3 = new Vehicle("tesla Model 3", driver1);
        driverService.saveVehicle(bmw5);
        driverService.saveVehicle(model3);

        Driver driver2 = new Driver("Mark");
        driverService.savePerson(driver2);

        Vehicle bmw6 = new Vehicle("BMW 6", driver2);
        Vehicle model5x = new Vehicle("tesla Model 5X");
        Vehicle honda = new Vehicle("Honda Civil", driver2);
        driverService.saveVehicle(bmw6);
        driverService.saveVehicle(model5x);
        driverService.saveVehicle(honda);


        log.info("=========== create Vehicle end ==========");
        driverService.queryPerson().forEach(e-> log.info("{}", e.toString()));
        driverService.queryVehicle().forEach(e-> log.info("{}", e.toString()));
        log.info("=========== query Vehicle end ==========");
    }
}
