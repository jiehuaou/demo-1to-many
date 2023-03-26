package com.example.demo.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * demo : get list of single Pojo from Repository query.
 */
@Slf4j
@Order(501)
@Component("sql-result-map1-runner")
public class SqlResultSetMapping1Runner implements CommandLineRunner {
    @Autowired
    private CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
        log.info("================== sql-result-map1 begin ==================");
        String[][] data = new String[][] {
                {"John", "person"},{"Joe", "person"},{"AWS", "company"},{"Google", "company"},
        };
        Arrays.stream(data).forEach(array->{
            customerService.saveCustomer(new Customer(array[0], array[1]));
        });

        customerService.totalCustomersByType()
                .stream()
                .forEach(customerTypeDTO -> log.info("DTO ---> {}", customerTypeDTO));

        log.info("================== sql-result-map1 end ==================");
    }
}
