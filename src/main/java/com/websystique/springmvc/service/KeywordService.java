package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.KeywordDao;
import com.websystique.springmvc.model.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class KeywordService {

    @Autowired
    private KeywordDao keywordDao;

    public Keyword findById(int id) {
        return keywordDao.findById(id);
    }

    public void save(Keyword keyword) {
        keywordDao.save(keyword);
    }

    public List<Keyword> findAll() {
        return keywordDao.findAll();
    }

    public Keyword findByKey(String key) {
        return keywordDao.findByKey(key);
    }
}
