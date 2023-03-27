package com.example.demo.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

/**
 * demo : get list of Pojo Array from Repository query.
 */
@Slf4j
@Order(502)
@Component("sql-result-map2-runner")
public class SqlResultSetMapping2Runner implements CommandLineRunner {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {
        log.info("================== sql-result-map 2 begin ==================");

        try{
            // Cannot create TypedQuery for query with more than one return

            customerRepository.totalCustomersByType2()
                    .stream()
                    .forEach(arr -> log.info("{} ---> {}", arr[0], arr[1]));
        } catch (Exception ex) {
            log.info("", ex);
        }



        log.info("================== sql-result-map 2 end ==================");
    }
}
