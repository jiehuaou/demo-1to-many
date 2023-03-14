package com.example.demo.uni.join_table;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class PersonService {
    private final PersonStore personStore;
    private final VehicleStore vehicleStore;

    public PersonService(PersonStore personStore, VehicleStore vehicleStore) {
        this.personStore = personStore;
        this.vehicleStore = vehicleStore;
    }

    public List<Person> queryPerson() {
        return personStore.findAll();
    }

    public List<Vehicle> queryVehicle() {
        return vehicleStore.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void savePerson(Person person) {
        personStore.save(person);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveVehicle(Vehicle vehicle) {
        vehicleStore.save(vehicle);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteVehicle(Vehicle vehicle) {
        vehicleStore.delete(vehicle);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletePerson(Person person) {
        List<Vehicle> vehicleList = vehicleStore.findAllByPersonId(person.getId());
        vehicleList.forEach(vehicle->{
            log.info("remove relation of {}", vehicle.toString());
            vehicle.setPerson(null);
            vehicleStore.save(vehicle);
        });
        personStore.delete(person);
    }

}
