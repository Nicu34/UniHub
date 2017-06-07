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

    /**
     * Gets user by given id;
     * @param id
     * @return user with above criteria
     */
    public User findById(int id) {
        return getByKey(id);
    }

    /**
     * Gets user by given ssoid
     * @param sso
     * @return user with above criteria.
     */
    public User findBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        User user = (User) crit.uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getUniversity());
        }
        return user;
    }

    /**
     * Returns true or false depending if the user given ssoid is unique
     * @param sso
     * @return true / false
     */
    public User findOnlyBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        return (User) crit.uniqueResult();
    }

    /**
     * Gets all users by given university
     * @param university
     * @return list of users with above criteria
     */
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers(University university) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        criteria.add(Restrictions.eq("university", university));

        return (List<User>) criteria.list();
    }

    /**
     * Saves user into database.
     * @param user
     */
    public void save(User user) {
        persist(user);
    }

}
