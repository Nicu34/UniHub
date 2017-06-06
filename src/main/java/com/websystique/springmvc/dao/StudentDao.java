package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Student;
import com.websystique.springmvc.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nicu on 5/24/2017.
 */
@Repository
public class StudentDao extends AbstractDao<Integer, Student> {

    static final Logger logger = LoggerFactory.getLogger(StudentDao.class);

    public Student findById(Integer id) {
        return getByKey(id);
    }

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
    public void save(Student student) {
        persist(student);
    }

    public void deleteById(Integer id) {
        Student student = findById(id);
        delete(student);
    }

    @SuppressWarnings("unchecked")
    public List<Student> findAllStudents() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Student>) criteria.list();
    }
}