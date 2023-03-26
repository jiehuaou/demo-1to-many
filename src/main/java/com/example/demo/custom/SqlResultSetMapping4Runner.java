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
 * demo : get scalar value from EntityManager query,
 *
 */
@Slf4j
@Order(504)
@Component("sql-result-map4-runner")
public class SqlResultSetMapping4Runner implements CommandLineRunner {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
        log.info("================== sql-result-map 4 begin ==================");

        List<Object[]> data = entityManager.createNativeQuery("SELECT c.customer_type, COUNT(c.customer_type) AS count FROM Customer AS c GROUP BY c.customer_type")
                .getResultList();
        System.out.println(data);
        data.stream().forEach(arr->{
            log.info("{} ==> {}", arr[0], arr[1]);
        });
        log.info("================== sql-result-map 4 end ==================");
    }
}
