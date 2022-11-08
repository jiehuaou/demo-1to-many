package com.example.demo.many.one2many.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="q5991")
public class Question {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String qname;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="qid")
    @OrderColumn(name="type")
    private List<Answer> answers;
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
    public List<Answer> getAnswers() {
        return answers;
    }
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
