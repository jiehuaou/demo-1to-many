package com.example.demo.many2many.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ques1123")
public class ManyQuestion {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String qname;

    @ManyToMany(targetEntity = ManyAnswer.class, cascade = { CascadeType.ALL })
    @JoinTable(name = "q_ans1123",
            joinColumns = { @JoinColumn(name = "q_id") },
            inverseJoinColumns = { @JoinColumn(name = "ans_id") })
    private List<ManyAnswer> answers;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getQname() {
        return qname;
    }
    public void setQname(String qname) {
        this.qname = qname;
    }
    public List<ManyAnswer> getAnswers() {
        return answers;
    }
    public void setAnswers(List<ManyAnswer> answers) {
        this.answers = answers;
    }
}
