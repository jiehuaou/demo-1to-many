package com.example.demo.uni.many2one;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The unidirectional associations are not very efficient when it comes to removing child entities.
 * In this particular example, Hibernate deletes all database child entries
 * then re-insert the ones that are still found in the in-memory persistence context.
 */
@Slf4j
@Order(302)
@Component("delete-company1")
public class Many2OneDeleteRunner implements CommandLineRunner {

    @Autowired
    private Branch1Service branch1Service;
    @Autowired
    private Branch1Store branch1Store;


    @Override
    public void run(String... args) throws Exception {
        log.info("=========== delete company begin ==========");

        Integer branchId = branch1Service.getBranchId();
        branch1Store.findById(branchId).ifPresent(branch1 -> {
            branch1Service.deleteBranch1(branch1);
        });
        log.info("=========== delete company end ==========");
        List<Branch1> branch1List = branch1Service.queryBranch1();
        branch1List.forEach(e -> log.info(e.toString()));
        log.info("=========== query company end ==========");
    }
}
