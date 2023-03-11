package com.example.demo.uni.many2one;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Company1Store extends JpaRepository<Company1, Integer> {
    Optional<Company1> findByTitle(String title);
}
