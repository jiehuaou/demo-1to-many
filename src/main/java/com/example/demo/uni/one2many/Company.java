package com.example.demo.uni.one2many;

import javax.persistence.*;
import java.util.*;

/**
 * unidirectional associations
 *
 * using only @OneToMany then there will be 3 tables (company, branch and company_branch).
 *
 * The unidirectional associations are not very efficient when it comes to removing child entities.
 * Hibernate will delete all children then re-insert all others.
 *
 * company is parent,
 * branch is child,
 */
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(targetEntity=Branch.class,cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "company_id")
    private Set<Branch> branchList = new LinkedHashSet<>();
    //private List<Branch> branchList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Branch> getBranch() {
        return branchList;
    }

    public void removeBranch(Branch branch) {
        branchList.remove(branch);
    }
    public void addBranch(Branch branch) {
        branchList.add(branch);
    }

    public void setBranch(Set<Branch> branch) {
        this.branchList = new LinkedHashSet(branch);
        System.out.println(branchList);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", branch=" + branchList +
                '}';
    }
}
