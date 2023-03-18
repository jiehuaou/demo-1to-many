package com.example.demo.uni.one2many;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyStore companyStore;
    @Autowired
    private BranchStore branchStore;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    private Integer companyId = null;

    public CompanyService() {
        //
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(Company company) {
        return companyStore.save(company).getId();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCompany(Company company) {
        companyStore.delete(company);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBranch(Branch branch) {
        branchStore.delete(branch);
    }

    public List<Company> queryCompany() {
        return companyStore.findAll();
    }
}
