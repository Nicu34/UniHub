package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Document;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DocumentDao extends AbstractDao<Integer, Document> {

    public Document findById(int id) {
        Document document = getByKey(id);

        if (document != null) {
            Hibernate.initialize(document.getTemplatesList());
        }

        return document;
    }

    @SuppressWarnings("unchecked")
    public List<Document> findByTitle(String title) {
        Criteria crit = createEntityCriteria().addOrder(Order.asc("title"));
        crit.add(Restrictions.like("title", title));
        return (List<Document>) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Document> findAll() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("title"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return (List<Document>) criteria.list();
    }

    public void save(Document user) {
        persist(user);
    }
}