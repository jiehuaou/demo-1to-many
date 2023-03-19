package com.example.demo.uni.one2many;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SetBranchStore extends JpaRepository<SetBranch, Integer> {
    Optional<SetBranch> findByName(String name);
}
