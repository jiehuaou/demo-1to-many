package com.example.demo.many2many.data;

import javax.persistence.*;

@Entity
@Table(name="ans1123")
public class ManyAnswer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String answerName;
    private String postedBy;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAnswername() {
        return answerName;
    }
    public void setAnswername(String answername) {
        this.answerName = answername;
    }
    public String getPostedBy() {
        return postedBy;
    }
    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }
}
