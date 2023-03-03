package com.example.demo.uni.one2many;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyService {
     private final CompanyStore companyStore;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    private Integer companyId = null;

    public CompanyService(CompanyStore companyStore) {
        this.companyStore = companyStore;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(Company company) {
        return companyStore.save(company).getId();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Company company) {
        companyStore.delete(company);
    }

    public List<Company> queryCompany() {
        return companyStore.findAll();
    }
}
