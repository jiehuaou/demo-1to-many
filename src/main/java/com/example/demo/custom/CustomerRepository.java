package com.example.demo.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(nativeQuery = true)
    List<CustomerTypeCount> totalCustomersByType();

    @Query(nativeQuery = true)
    List<Object[]> totalCustomersByType2();
}
