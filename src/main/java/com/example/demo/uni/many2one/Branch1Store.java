package com.example.demo.uni.many2one;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Branch1Store extends JpaRepository<Branch1, Integer> {
    Optional<Branch1> findByName(String name);
}
