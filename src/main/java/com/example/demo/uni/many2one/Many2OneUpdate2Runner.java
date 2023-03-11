package com.example.demo.uni.many2one;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * update existing Branch1 (ManyToOne) with new company
 */

@Slf4j
@Order(303)
@Component("update2-company1")
public class Many2OneUpdate2Runner implements CommandLineRunner {

    @Autowired
    private Branch1Service branch1Service;
    @Autowired
    private Branch1Store branch1Store;
    @Autowired
    private Company1Store company1Store;

    @Override
    public void run(String... args) throws Exception {
        log.info("=========== update company1 begin ==========");
        Company1 company = new Company1("company-2");
        branch1Service.saveCompany1(company);

        branch1Store.findByName("first branch").ifPresent(branch1 -> {
            branch1.setCompany1(company);
            branch1Service.saveBranch1(branch1);  // CascadeType.MERGE happen
        });

        branch1Store.findByName("second branch").ifPresent(branch1 -> {
            branch1.setCompany1(company);
            branch1Service.saveBranch1(branch1);  // CascadeType.MERGE happen
        });


        log.info("=========== update company1 end with BranchId {} ==========", 0);
        List<Branch1> branch1List = branch1Service.queryBranch1();
        branch1List.forEach(e -> log.info(e.toString()));
        List<Company1> company1List = branch1Service.queryCompany1();
        company1List.forEach(e -> log.info(e.toString()));
        log.info("=========== query company1 end ==========");
    }
}
