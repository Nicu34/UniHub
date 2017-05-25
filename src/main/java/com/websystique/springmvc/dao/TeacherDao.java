package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Teacher;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nicu on 5/24/2017.
 */
@Repository
public class TeacherDao extends AbstractDao<Integer, Teacher> {

    static final Logger logger = LoggerFactory.getLogger(TeacherDao.class);

    public Teacher findById(Integer id) {
        Teacher teacher = getByKey(id);
        if (teacher != null) {
            Hibernate.initialize(teacher.getCourses());
            Hibernate.initialize(teacher.getSchoolGroups());
        }

        return teacher;
    }

    public void save(Teacher teacher) {
        persist(teacher);
    }

    public void deleteById(Integer id) {
        Teacher teacher = findById(id);
        delete(teacher);
    }

    @SuppressWarnings("unchecked")
    public List<Teacher> findAllTeachers() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Teacher>) criteria.list();
    }
}