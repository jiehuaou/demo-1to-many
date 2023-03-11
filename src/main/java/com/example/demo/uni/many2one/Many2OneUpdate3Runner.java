package com.example.demo.uni.many2one;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * update existing Branch1 (ManyToOne) with existing company
 */

@Slf4j
@Order(304)
@Component("update3-company1")
public class Many2OneUpdate3Runner implements CommandLineRunner {

    @Autowired
    private Branch1Service branch1Service;
    @Autowired
    private Branch1Store branch1Store;
    @Autowired
    private Company1Store company1Store;

    @Override
    public void run(String... args) throws Exception {
        log.info("=========== update company1 begin ==========");
        Optional<Company1> company1 = company1Store.findByTitle("company-2");
        Optional<Branch1> branch1 = branch1Store.findByName("forth branch");
        if(company1.isPresent() && branch1.isPresent()) {
            branch1.get().setCompany1(company1.get());
            branch1Service.saveBranch1(branch1.get());
        }

//        branch1Store.findByName("second branch").ifPresent(branch1 -> {
//            branch1.setCompany1(company);
//            branch1Service.saveBranch1(branch1);  // CascadeType.MERGE happen
//        });


        log.info("=========== update company1 end with BranchId {} ==========", 0);
        List<Branch1> branch1List = branch1Service.queryBranch1();
        branch1List.forEach(e -> log.info(e.toString()));
        List<Company1> company1List = branch1Service.queryCompany1();
        company1List.forEach(e -> log.info(e.toString()));
        log.info("=========== query company1 end ==========");
    }
}
