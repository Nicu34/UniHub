package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.ScoreDao;
import com.websystique.springmvc.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by nicu on 6/7/2017.
 */
@Service
@Transactional
public class ScoreService {

    @Autowired
    private ScoreDao questionDao;

    public void save(Score question) {
        questionDao.save(question);
    }

    public ArrayList<Score> findAll() {
        return questionDao.findAll();
    }

    public Score getBestScore() {
        Score bestScore = findAll().get(0);

        for (Score score : findAll()) {
            if (compare(bestScore, score) == 0) {
                bestScore = score;
            }
        }

        return bestScore;
    }

    private int compare(Score score1, Score score2) {
        return score1.getCorrectAnswers().doubleValue() / score1.getAnswersNumber().doubleValue() > score2.getCorrectAnswers().doubleValue() / score2.getAnswersNumber().doubleValue() ? 1 : 0;
    }
}
