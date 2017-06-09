package com.websystique.springmvc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicu on 6/7/2017.
 */
@Entity
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answersList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.MERGE)
    private Answer correctAnswer;

    @OneToOne(cascade = CascadeType.MERGE)
    private Answer submittedAnswer;

    public Question() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<Answer> answersList) {
        this.answersList = answersList;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Answer getSubmittedAnswer() {
        return submittedAnswer;
    }

    public void setSubmittedAnswer(Answer submittedAnswer) {
        this.submittedAnswer = submittedAnswer;
    }
}
