package com.example.demo.uni.many2one;

import javax.persistence.*;

/**
 * only @ManyToOne then there will be 2 tables. Such as company, branch.
 *
 * Branch is the owning side since it has the foreign key association.
 */
@Entity
public class Branch1 {
    public Branch1(String name, Company1 company1) {
        this.name = name;
        this.company1 = company1;
    }
    public Branch1() {

    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company1 getCompany1() {
        return company1;
    }

    public void setCompany1(Company1 company1) {
        this.company1 = company1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer branchId;

    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Company1 company1;

    @Override
    public String toString() {
        return "Branch1{" +
                "branchId=" + branchId +
                ", name='" + name + '\'' +
                ", company1=" + company1 +
                '}';
    }
}
