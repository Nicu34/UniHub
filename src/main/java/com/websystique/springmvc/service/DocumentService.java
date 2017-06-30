package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.DocumentDao;
import com.websystique.springmvc.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DocumentService {

    @Autowired
    private DocumentDao documentDao;

    public Document findById(int id) {
        return documentDao.findById(id);
    }

    public void save(Document document) {
        documentDao.save(document);
    }

    public List<Document> findAll() {
        return documentDao.findAll();
    }

    public List<Document> findByTitle(String title) {
        return documentDao.findByTitle(title);
    }
}
