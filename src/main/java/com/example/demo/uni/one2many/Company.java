package com.example.demo.uni.one2many;

import javax.persistence.*;
import java.util.*;

/**
 * unidirectional associations
 *
 * using only @OneToMany then there will be 3 tables (company, branch and company_branch).
 *
 * The unidirectional associations are not very efficient when it comes to removing child entities.
 * Hibernate will delete all children then re-insert all others in join table
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

    @OneToMany(targetEntity=SetBranch.class,cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinTable(name = "company_set_branch")
    private Set<SetBranch> branchList = new LinkedHashSet<>();   // use Set


    @OneToMany(targetEntity=ListBranch.class,cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinTable(name = "company_list_branch")
    private List<ListBranch> otherBranchList = new ArrayList<>();  // use List

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



    public void removeBranch(SetBranch branch) {
        branchList.remove(branch);
    }
    public void addBranch(SetBranch branch) {
        branchList.add(branch);
    }

    public Set<SetBranch> getBranch() {
        return branchList;
    }
    public void setBranch(Set<SetBranch> branch) {
        this.branchList = new LinkedHashSet(branch);
        System.out.println(branchList);
    }
    public void removeListBranch(ListBranch otherBranch) {
        otherBranchList.remove(otherBranch);
    }
    public void addListBranch(ListBranch otherBranch) {
        otherBranchList.add(otherBranch);
    }

    public List<ListBranch> getOtherBranchList() {
        return otherBranchList;
    }

    public void setOtherBranchList(List<ListBranch> otherBranchList) {
        this.otherBranchList = otherBranchList;
    }



    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", \n   setBranchList=" + branchList +
                ", \n   otherBranchList=" + otherBranchList +
                '}';
    }
}
