package com.example.demo.one2many.data;

import javax.persistence.*;

/**
 * Answer is owning side, with JoinColumn
 * Question is inverse side, with mappedBy
 */
@Entity
@Table(name="ans5991")
public class Answer {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)

    private int id;
    private String answername;
    private String postedBy;

    /**
     * ManyToOne must not define mappedBy
     */
    @ManyToOne()
    @JoinColumn(name = "question_id")
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAnswername() {
        return answername;
    }
    public void setAnswername(String answername) {
        this.answername = answername;
    }
    public String getPostedBy() {
        return postedBy;
    }
    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String toSimpleString() {
        return "Answer{" +
                "id=" + id +
                ", answername='" + answername + '\'' +
                ", postedBy='" + postedBy + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answername='" + answername + '\'' +
                ", postedBy='" + postedBy + '\'' +
                ", question=" + question.toSimpleString() +
                '}';
    }
}
