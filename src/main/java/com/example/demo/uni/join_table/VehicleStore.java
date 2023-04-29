package com.example.demo.uni.join_table;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleStore extends JpaRepository<Vehicle, Integer> {
    Optional<Vehicle> findByName(String name);
    List<Vehicle> findAllByDriverId(Integer driverId);
}
