package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.TemplateDao;
import com.websystique.springmvc.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TemplateService {

    @Autowired
    private TemplateDao templateDao;

    public Template findById(int id) {
        return templateDao.findById(id);
    }

    public void save(Template template) {
        templateDao.save(template);
    }

    public List<Template> findAll() {
        return templateDao.findAll();
    }
}
