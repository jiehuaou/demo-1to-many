package com.example.demo.uni.join_table;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * delete existing Vehicle belonging to Nobody
 *
 * delete from vehicle_person where vehicle_id=?
 * delete from vehicle where id=?
 *
 */

@Slf4j
@Order(406)
@Component("delete-2-Vehicle")
public class VehicleDelete2Runner implements CommandLineRunner {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonStore personStore;

    @Autowired
    private VehicleStore vehicleStore;


    @Override
    public void run(String... args) throws Exception {
        log.info("=========== delete 2 Vehicle begin ==========");

        //Optional<Person> person = personStore.findByName("Mark");
        Optional<Vehicle> tesla5 = vehicleStore.findByName("tesla Model 5X");
        if(tesla5.isPresent()) {
            personService.deleteVehicle(tesla5.get());
        }

        log.info("=========== delete 2 Vehicle end ==========");
        personService.queryPerson().forEach(e-> log.info("{}", e.toString()));
        personService.queryVehicle().forEach(e-> log.info("{}", e.toString()));
        log.info("=========== query Vehicle end ==========");
    }
}
