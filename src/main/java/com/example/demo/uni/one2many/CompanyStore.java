package com.example.demo.uni.one2many;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyStore extends JpaRepository<Company, Integer> {
}
