package com.example.demo.uni.one2many;

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
@Order(202)
@Component("delete-company")
public class CompanyDeleteRunner implements CommandLineRunner {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyStore companyStore;


    @Override
    public void run(String... args) throws Exception {
        log.info("=========== delete company begin ==========");

        Integer companyId = companyService.getCompanyId();
        companyStore.findById(companyId).ifPresent(company -> {
            Branch branch1 = company.getBranch().get(0);
            company.getBranch().remove(branch1);    // Hibernate delete all branch then re-insert branch2 and branch3.
            companyService.save(company);
        });
        log.info("=========== delete company end ==========");
        List<Company> companyList = companyService.queryCompany();
        companyList.forEach(e -> log.info(e.toString()));
        log.info("=========== query company end ==========");
    }
}
