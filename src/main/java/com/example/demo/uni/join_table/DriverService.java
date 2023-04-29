package com.example.demo.uni.join_table;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DriverService {
    private final DriverStore driverStore;
    private final VehicleStore vehicleStore;

    public DriverService(DriverStore driverStore, VehicleStore vehicleStore) {
        this.driverStore = driverStore;
        this.vehicleStore = vehicleStore;
    }

    public List<Driver> queryPerson() {
        return driverStore.findAll();
    }

    public List<Vehicle> queryVehicle() {
        return vehicleStore.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void savePerson(Driver driver) {
        driverStore.save(driver);
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
    public void deletePerson(Driver driver) {
        List<Vehicle> vehicleList = vehicleStore.findAllByDriverId(driver.getId());
        vehicleList.forEach(vehicle->{
            log.info("remove relation of {}", vehicle.toString());
            vehicle.setDriver(null);
            vehicleStore.save(vehicle);
        });
        driverStore.delete(driver);
    }

}
