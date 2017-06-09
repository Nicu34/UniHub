package com.websystique.springmvc.model;

/**
 * Created by nicu on 6/7/2017.
 */
import com.websystique.springmvc.dto.QuestionDto;

import java.util.List;

public class QuestionForm {

    private List<QuestionDto> questions;

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }
}
