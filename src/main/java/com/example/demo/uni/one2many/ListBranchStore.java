package com.example.demo.uni.one2many;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListBranchStore extends JpaRepository<ListBranch, Integer> {
    Optional<ListBranch> findByName(String name);
}
