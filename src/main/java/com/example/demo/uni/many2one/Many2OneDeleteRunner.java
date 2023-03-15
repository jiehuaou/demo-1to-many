package com.example.demo.uni.many2one;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * deleting Branch (@ManyToOne and @JoinColumn) is quite simple here, only one SQL
 *
 * delete from branch1 where branch_id=?
 */
@Slf4j
@Order(309)
@Component("delete-company1")
public class Many2OneDeleteRunner implements CommandLineRunner {

    @Autowired
    private Branch1Service branch1Service;
    @Autowired
    private Branch1Store branch1Store;


    @Override
    public void run(String... args) throws Exception {
        log.info("=========== delete Branch begin ==========");

        Integer branchId = branch1Service.getBranchId();
        branch1Store.findById(branchId).ifPresent(branch1 -> {
            branch1Service.deleteBranch1(branch1);
        });
        log.info("=========== delete Branch end ==========");
        List<Branch1> branch1List = branch1Service.queryBranch1();
        branch1List.forEach(e -> log.info(e.toString()));
        List<Company1> company1List = branch1Service.queryCompany1();
        company1List.forEach(e -> log.info(e.toString()));
        log.info("=========== query Branch end ==========");
    }
}
