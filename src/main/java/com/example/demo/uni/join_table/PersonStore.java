package com.example.demo.uni.join_table;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonStore extends JpaRepository<Person, Integer> {
    Optional<Person> findByName(String name);
}
