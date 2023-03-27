package com.example.demo.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * returning Single Pojo is fine in Jpa Repository.
     */
    @Query(nativeQuery = true)
    List<CustomerTypeCount> totalCustomersByType();

    /**
     * returning Multiple Pojo can not work in Jpa Repository.
     */
    @Query(nativeQuery = true)
    List<Object[]> totalCustomersByType2();

    /**
     * returning Multiple Scalar Values can work in Jpa Repository.
     */
    @Query(value = "SELECT c.customer_type, COUNT(c.customer_type) AS customer_count FROM Customer AS c GROUP BY c.customer_type" , nativeQuery = true)
    List<Object[]> totalCustomersByType3();
}
