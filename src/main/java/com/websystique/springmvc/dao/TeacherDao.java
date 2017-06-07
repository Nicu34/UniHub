package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Teacher;
import com.websystique.springmvc.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by nicu on 5/24/2017.
 */
@Repository
public class TeacherDao extends AbstractDao<Integer, Teacher> {

    static final Logger logger = LoggerFactory.getLogger(TeacherDao.class);

    /**
     * Gets teacher by given id
     * @param id
     * @return teacher with above criteria.
     */
    public Teacher findById(Integer id) {
        Teacher teacher = getByKey(id);
        if (teacher != null) {
            Hibernate.initialize(teacher.getCourses());
        }

        return teacher;
    }

    /**
     * Gets teacher by given user
     * @param user
     * @return teacher with above criteria.
     */
    public Teacher findByUser(User user) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("user", user));
        Teacher teacher = ((Teacher) crit.uniqueResult());

        if (teacher != null) {
            Hibernate.initialize(teacher.getCourses());
        }

        return teacher;
    }

    /**
     * Saves a teacher into database.
     * @param teacher
     */
    public void save(Teacher teacher) {
        persist(teacher);
    }
}