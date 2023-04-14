package com.example.demo.uni.one2many;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * delete child from Parent ( @OneToMany side ),
 *
 * The unidirectional @OneToMany associations are not efficient when removing child.
 * In this particular example, Hibernate deletes all database child entries
 * then re-insert the ones that are still found in the in-memory persistence context.
 */
@Slf4j
@Order(202)
@Component("delete1-company1-202")
public class CompanyDelete1Runner implements CommandLineRunner {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyStore companyStore;


    @Override
    public void run(String... args) throws Exception {
        log.info("=========== delete company begin ==========");

        Integer companyId = companyService.getCompanyId();
        companyStore.findById(companyId).ifPresent(company -> {
            SetBranch branch1 = company.getBranch().iterator().next();

            // remove child From Set
            company.removeBranchFromSet(branch1);            // simply delete in join table

            companyService.save(company);
        });
        log.info("-------------------------------------------------");
        companyStore.findById(companyId).ifPresent(company -> {
            SetBranch branch1 = company.getBranch().iterator().next();
            ListBranch listBranch1 = company.getOtherBranchList().get(0);

             // remove child From List
            company.removeBranchFromList(listBranch1);       // Hibernate delete all branch then re-insert branch2,branch3 in join table

            companyService.save(company);
        });
        log.info("=========== delete company end ==========");
        List<Company> companyList = companyService.queryCompany();
        companyList.forEach(e -> log.info(e.toString()));
        log.info("=========== query company end ==========");
    }
}
