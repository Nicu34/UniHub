package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Score;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by nicu on 6/7/2017.
 */
@Repository
@Transactional
public class ScoreDao extends AbstractDao<Integer, Score>{
    public void save(Score score) {
        persist(score);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Score> findAll() {

        return (ArrayList<Score>) createEntityCriteria().list();
    }
}
