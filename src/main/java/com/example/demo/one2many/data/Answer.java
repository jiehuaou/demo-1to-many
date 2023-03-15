package com.example.demo.one2many.data;

import javax.persistence.*;

/**
 * Answer( @ManyToOne ) is owning side (child side), with JoinColumn
 * Question( @OneToMany ) is inverse side (parent side), with mappedBy
 *
 * child side must re-implement equals() and hashCode()
 */
@Entity
@Table(name="Answer_table")
public class Answer {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer id;
    private String answername;
    private String postedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return getId() != null && getId() == answer.getId();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    /**
     * ManyToOne must not define mappedBy
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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
