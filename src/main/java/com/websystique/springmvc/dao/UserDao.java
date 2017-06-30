package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.User;
import org.hibernate.Criteria;
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
        return (User) crit.uniqueResult();
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
     * @return list of users with above criteria
     */
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.

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
