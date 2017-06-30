package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Keyword;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class KeywordDao extends AbstractDao<Integer, Keyword> {

    public Keyword findById(int id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<Keyword> findAll() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("key"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return (List<Keyword>) criteria.list();
    }

    public Keyword findByKey(String key) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("key", key));
        return (Keyword) crit.uniqueResult();
    }

    public void save(Keyword user) {
        persist(user);
    }
}