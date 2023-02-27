package com.example.demo.one2many.data;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Answer is owning side, with JoinColumn
 * Question is inverse side, with mappedBy
 */
@Entity
@Table(name="q5991")
public class Question {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String qname;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="question")
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
        answers.stream().forEach(e->e.setQuestion(this));
        this.answers = answers;
    }


    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", qname='" + qname + '\'' +
                ", answers=" + answers.stream().map(e->e.toSimpleString()).collect(Collectors.toList()) +
                '}';
    }

    public String toSimpleString() {
        return "Question{" +
                "id=" + id +
                ", qname='" + qname + '\'' +
                '}';
    }
}
