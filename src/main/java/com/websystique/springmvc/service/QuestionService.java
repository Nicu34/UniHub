package com.websystique.springmvc.service;

import com.websystique.springmvc.controller.QuestionListWrapper;
import com.websystique.springmvc.dao.QuestionDao;
import com.websystique.springmvc.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by nicu on 6/7/2017.
 */
@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public void save(Question question) {
        questionDao.save(question);
    }

    public Set<Question> findAll(Integer limit) {
        return questionDao.findAll(limit);
    }

    public int getCorrectQuestionsNumber(QuestionListWrapper questionListWrapper) {
        return ((int) questionListWrapper.getQuestions().stream().filter(question -> question.getCorrectAnswer().equals(question.getSubmittedAnswer())).count());
    }
}
