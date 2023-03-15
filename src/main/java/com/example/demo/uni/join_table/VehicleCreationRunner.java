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
    private PersonService personService;


    @Override
    public void run(String... args) throws Exception {
        log.info("=========== create Vehicle begin ==========");
        Person person1 = new Person("Joe");
        personService.savePerson(person1);

        Vehicle bmw5 = new Vehicle("BMW 5", person1);
        Vehicle model3 = new Vehicle("tesla Model 3", person1);
        personService.saveVehicle(bmw5);
        personService.saveVehicle(model3);

        Person person2 = new Person("Mark");
        personService.savePerson(person2);

        Vehicle bmw6 = new Vehicle("BMW 6", person2);
        Vehicle model5x = new Vehicle("tesla Model 5X");
        Vehicle honda = new Vehicle("Honda Civil", person2);
        personService.saveVehicle(bmw6);
        personService.saveVehicle(model5x);
        personService.saveVehicle(honda);


        log.info("=========== create Vehicle end ==========");
        personService.queryPerson().forEach(e-> log.info("{}", e.toString()));
        personService.queryVehicle().forEach(e-> log.info("{}", e.toString()));
        log.info("=========== query Vehicle end ==========");
    }
}
