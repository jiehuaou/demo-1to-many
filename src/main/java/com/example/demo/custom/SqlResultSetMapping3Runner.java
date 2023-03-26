package com.example.demo.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * demo : get list of Pojo Array from EntityManager query.
 */
@Slf4j
@Order(503)
@Component("sql-result-map3-runner")
public class SqlResultSetMapping3Runner implements CommandLineRunner {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {
        log.info("================== sql-result-map 3 begin ==================");

        List<Object[]> data = entityManager.createNamedQuery("Customer.totalCustomersByType2").getResultList();
        data.stream().forEach(arr->{
            log.info("{} --> {}", arr[0], arr[1]);
        });

        log.info("================== sql-result-map 3 end ==================");
    }
}
