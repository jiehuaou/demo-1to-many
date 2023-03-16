package com.example.demo.one2many.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Answer( @ManyToOne ) is owning side, with JoinColumn
 * Question( @OneToMany ) is inverse side, with mappedBy
 */
@Entity
@Table(name="Question_table")
public class Question {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String qname;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy="question")
    private List<Answer> answers = new ArrayList<>();
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

    public void addAnswer(Answer answer) {
        answers.add(answer);
        answer.setQuestion(this);
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
        answer.setQuestion(null);
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
