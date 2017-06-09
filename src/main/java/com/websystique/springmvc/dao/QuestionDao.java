package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Question;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by nicu on 6/7/2017.
 */
@Repository
@Transactional
public class QuestionDao extends AbstractDao<Integer, Question>{
    public void save(Question schoolGroup) {
        persist(schoolGroup);
    }

    @SuppressWarnings("unchecked")
    public Set<Question> findAll(Integer limit) {

        Set<Question> questions = new HashSet<>((List<Question>) createEntityCriteria().list().stream().limit(limit).collect(Collectors.toList()));

        questions.forEach(question -> Hibernate.initialize(question.getAnswersList()));

        return questions;
    }
}
