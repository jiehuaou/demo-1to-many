package com.example.demo.uni.many2one;

import javax.persistence.*;


/**
 * only @ManyToOne then there will be 2 tables. Such as company, branch.
 *
 * Branch is the owning side since it has the foreign key association.
 */
@Entity
public class Company1 {
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer companyId;

    private String title;

    @Override
    public String toString() {
        return "Company1{" +
                "companyId=" + companyId +
                ", title='" + title + '\'' +
                '}';
    }
}
