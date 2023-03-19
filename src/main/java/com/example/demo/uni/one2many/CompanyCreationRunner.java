package com.example.demo.uni.one2many;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Order(201)
@Component("create-company")
public class CompanyCreationRunner implements CommandLineRunner {

    @Autowired
    private CompanyService companyService;

    @Override
    public void run(String... args) throws Exception {
        log.info("=========== create company begin ==========");
        Company company = new Company();
        company.setName("company");
        //Set<Branch> branchs = new HashSet<>();
        SetBranch branch1 = new SetBranch("first branch");
        SetBranch branch2 = new SetBranch("second branch");
        SetBranch branch3 = new SetBranch("third branch");

        company.addBranch(branch1);
        company.addBranch(branch2);
        company.addBranch(branch3);

        company.addListBranch(new ListBranch("first other"));
        company.addListBranch(new ListBranch("second other"));
        company.addListBranch(new ListBranch("third other"));

        Integer id = companyService.save(company);
        companyService.setCompanyId(id);
        log.info("=========== create company end with compantId {} ==========", id);
        List<Company> companyList = companyService.queryCompany();
        companyList.forEach(e -> log.info(e.toString()));
        log.info("=========== query company end ==========");
    }
}
