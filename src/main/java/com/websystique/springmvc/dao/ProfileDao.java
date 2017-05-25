package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Profile;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProfileDao extends AbstractDao<Integer, Profile> {

    public Profile findById(int id) {
        return getByKey(id);
    }

    public Profile findByType(String type) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("type", type));
        return (Profile) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Profile> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("type"));
        return (List<Profile>) criteria.list();
    }

}
