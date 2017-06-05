package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.InvitedUser;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nicu on 6/5/2017.
 */
@Repository
public class InvitedUserDao extends AbstractDao<Integer, InvitedUser> {

    public InvitedUser findById(int id) {
        return getByKey(id);
    }

    public InvitedUser findByEmail(String email) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("email", email));
        InvitedUser user = (InvitedUser) crit.uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getUniversity());
        }
        return user;
    }

    public InvitedUser findBySsoId(String ssoId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", ssoId));
        InvitedUser user = (InvitedUser) crit.uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getUniversity());
            Hibernate.initialize(user.getSchoolGroup());
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<InvitedUser> findAllInvitedUsers() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("email"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.

        return (List<InvitedUser>) criteria.list();

    }

    public void save(InvitedUser user) {
        persist(user);
    }

    public void deleteByEmail(String email) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("email", email));
        InvitedUser user = (InvitedUser) crit.uniqueResult();
        delete(user);
    }

}
