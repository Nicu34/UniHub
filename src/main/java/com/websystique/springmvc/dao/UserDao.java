package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.University;
import com.websystique.springmvc.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDao extends AbstractDao<Integer, User> {

    public User findById(int id) {
        User user = getByKey(id);
        if (user != null) {
            Hibernate.initialize(user.getProfiles());
        }
        return user;
    }

    public User findBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        User user = (User) crit.uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getProfiles());
            Hibernate.initialize(user.getUniversity());
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers(University university) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        criteria.add(Restrictions.eq("university", university));

        List<User> userList = (List<User>) criteria.list();

        userList.forEach(user -> Hibernate.initialize(user.getProfiles()));
        return userList;
    }

    public void save(User user) {
        persist(user);
    }

    public void deleteBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        User user = (User) crit.uniqueResult();
        delete(user);
    }

}
