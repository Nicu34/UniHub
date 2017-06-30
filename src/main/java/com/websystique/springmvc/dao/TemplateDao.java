package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Template;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TemplateDao extends AbstractDao<Integer, Template> {

    public Template findById(int id) {
        return getByKey(id);
    }


    @SuppressWarnings("unchecked")
    public List<Template> findAll() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return (List<Template>) criteria.list();
    }

    public void save(Template user) {
        persist(user);
    }
}
