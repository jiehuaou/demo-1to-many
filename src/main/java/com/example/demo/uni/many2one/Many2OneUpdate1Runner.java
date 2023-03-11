package com.example.demo.uni.many2one;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * create Branch1 (ManyToOne) with existing company
 */

@Slf4j
@Order(302)
@Component("update1-company1")
public class Many2OneUpdate1Runner implements CommandLineRunner {

    @Autowired
    private Branch1Service branch1Service;
    @Autowired
    private Company1Store company1Store;

    @Override
    public void run(String... args) throws Exception {
        log.info("=========== update company1 begin ==========");
        Company1 company = company1Store.getById(branch1Service.getCompanyId());

        Branch1 branch5 = new Branch1("forth branch", company);
        branch1Service.saveBranch1(branch5);    // CascadeType.PERSIST happen

        log.info("=========== update company1 end with BranchId {} ==========", branch5.getBranchId());
        List<Branch1> branch1List = branch1Service.queryBranch1();
        branch1List.forEach(e -> log.info(e.toString()));
        List<Company1> company1List = branch1Service.queryCompany1();
        company1List.forEach(e -> log.info(e.toString()));
        log.info("=========== query company1 end ==========");
    }
}
