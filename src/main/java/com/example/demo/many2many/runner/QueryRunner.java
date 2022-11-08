package com.example.demo.many2many.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(4)
@Component
public class QueryRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

    }
}
