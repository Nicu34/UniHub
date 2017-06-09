package com.websystique.springmvc.dto;

/**
 * Created by nicu on 6/7/2017.
 */
public class QuestionDto {
    private Integer questionId;

    private Integer answerId;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }
}
