package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Student;
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
public class StudentDao extends AbstractDao<Integer, Student> {

    static final Logger logger = LoggerFactory.getLogger(StudentDao.class);

    /**
     * Finds a student by given id;
     * @param id
     * @return student with above critera
     */
    public Student findById(Integer id) {
        return getByKey(id);
    }

    /**
     * Finds student by given user;
     * @param user
     * @return student with above criteria.
     */
    public Student findByUser(User user) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("user", user));
        Student student = ((Student) crit.uniqueResult());

        if (student != null) {
            Hibernate.initialize(student.getSchoolGroup());
            Hibernate.initialize(student.getSchoolGroup().getStudyYear());
            Hibernate.initialize(student.getSchoolGroup().getStudyYear().getCourseList());
        }

        return student;
    }

    /**
     * Saves a student into database
     * @param student
     */
    public void save(Student student) {
        persist(student);
    }
}