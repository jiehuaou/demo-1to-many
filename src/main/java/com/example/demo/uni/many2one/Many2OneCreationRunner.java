package com.example.demo.uni.many2one;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Order(301)
@Component("create-company1")
public class Many2OneCreationRunner implements CommandLineRunner {

    @Autowired
    private Branch1Service branch1Service;

    @Override
    public void run(String... args) throws Exception {
        log.info("=========== create company1 begin ==========");
        Company1 company = new Company1();
        company.setTitle("company-1");
        branch1Service.saveCompany1(company);

        Branch1 branch1 = new Branch1("first branch", company);
        Branch1 branch2 = new Branch1("second branch", company);
        Branch1 branch3 = new Branch1("third branch", company);

        branch1Service.saveBranch1(branch1);
        branch1Service.saveBranch1(branch2);
        branch1Service.saveBranch1(branch3);


//        List<Integer> ids = branch1Service.saveManyBranch1(branch1, branch2, branch3);
        branch1Service.setBranchId(branch1.getBranchId());
        branch1Service.setCompanyId(branch1.getCompany1().getCompanyId());

        log.info("=========== create company1 end with BranchId {} ==========", branch1Service.getBranchId());
        List<Branch1> branch1List = branch1Service.queryBranch1();
        branch1List.forEach(e -> log.info(e.toString()));
        List<Company1> company1List = branch1Service.queryCompany1();
        company1List.forEach(e -> log.info(e.toString()));
        log.info("=========== query company1 end ==========");
    }
}
