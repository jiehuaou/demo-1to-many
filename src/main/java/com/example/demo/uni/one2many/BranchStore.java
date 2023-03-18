package com.example.demo.uni.one2many;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchStore extends JpaRepository<Branch, Integer> {
    Optional<Branch> findByName(String name);
}
