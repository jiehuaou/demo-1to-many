package com.example.demo.github.test01;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category00, Long> {
    Category00 findByUsers_Name(String name);

}
