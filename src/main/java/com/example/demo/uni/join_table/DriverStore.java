package com.example.demo.uni.join_table;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverStore extends JpaRepository<Driver, Integer> {
    Optional<Driver> findByName(String name);
}
