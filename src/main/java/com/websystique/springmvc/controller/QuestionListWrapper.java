package com.websystique.springmvc.controller;

import com.websystique.springmvc.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by nicu on 6/7/2017.
 */
public class QuestionListWrapper {
    private List<Question> questions = new ArrayList<>();

    public QuestionListWrapper() {
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = new ArrayList<>(questions);
    }
}
