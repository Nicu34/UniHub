package com.websystique.springmvc.dto;

/**
 * Created by nicu on 6/9/2017.
 */
public class QuizDto {
    private String questionsNumber;
    private String questionsPerPage;

    public String getQuestionsNumber() {
        return questionsNumber;
    }

    public void setQuestionsNumber(String questionsNumber) {
        this.questionsNumber = questionsNumber;
    }

    public String getQuestionsPerPage() {
        return questionsPerPage;
    }

    public void setQuestionsPerPage(String questionsPerPage) {
        this.questionsPerPage = questionsPerPage;
    }
}
