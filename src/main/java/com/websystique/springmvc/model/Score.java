package com.websystique.springmvc.model;

import javax.persistence.*;

/**
 * Created by nicu on 6/8/2017.
 */
@Entity
public class Score {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer correctAnswers;

    @Column
    private Integer answersNumber;

    @Column
    private Integer wrongAnswers;

    public Score() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Integer correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Integer getAnswersNumber() {
        return answersNumber;
    }

    public void setAnswersNumber(Integer answersNumber) {
        this.answersNumber = answersNumber;
    }

    public Integer getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(Integer wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }
}
