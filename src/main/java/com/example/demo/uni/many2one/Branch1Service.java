package com.example.demo.uni.many2one;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Branch1Service {
     private final Branch1Store branch1Store;
     private final Company1Store company1Store;

    public Branch1Service(Branch1Store branch1Store, Company1Store company1Store) {
        this.branch1Store = branch1Store;
        this.company1Store = company1Store;
    }


    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    private Integer branchId = null;


    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveBranch1(Branch1 branch1) {
        return branch1Store.save(branch1).getBranchId();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Integer> saveManyBranch1(Branch1... branch1List) {
        List<Integer> list = Arrays.stream(branch1List)
                .map(e->branch1Store.save(e).getBranchId())
                .collect(Collectors.toList());
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveCompany1(Company1 company1) {
        return company1Store.save(company1).getCompanyId();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBranch1(Branch1 branch1) {
        branch1Store.delete(branch1);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCompany1(Company1 company1) {
        company1Store.delete(company1);
    }

    public List<Branch1> queryBranch1() {
        return branch1Store.findAll();
    }
}
