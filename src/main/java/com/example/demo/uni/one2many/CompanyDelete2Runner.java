package com.example.demo.uni.one2many;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * delete child via JPA repository directly,
 *
 */
@Slf4j
@Order(203)
@Component("delete1-company1-203")
public class CompanyDelete2Runner implements CommandLineRunner {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyStore companyStore;
    @Autowired
    private SetBranchStore branchStore;


    @Override
    public void run(String... args) throws Exception {
        log.info("=========== delete company begin ==========");

        Optional<SetBranch> optionalBranch = branchStore.findByName("third branch");
        optionalBranch.ifPresent(branch -> {
            // should show Integrity Violation here, when using join_table
            try {
                ; //companyService.deleteBranch(branch);
            } catch (Exception ex) {
                log.info("Data Integrity Violation when using join_table");
            }
        });
        log.info("=========== delete company end ==========");
        List<Company> companyList = companyService.queryCompany();
        companyList.forEach(e -> log.info(e.toString()));
        log.info("=========== query company end ==========");
    }
}
