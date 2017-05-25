package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Course;
import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nicu on 5/24/2017.
 */
@Repository
public class CourseDao extends AbstractDao<Integer, Course> {

    static final Logger logger = LoggerFactory.getLogger(CourseDao.class);

    public Course findById(Integer id) {
        return getByKey(id);
    }

    public void save(Course course) {
        persist(course);
    }

    public void deleteById(Integer id) {
        Course course = findById(id);
        delete(course);
    }

    @SuppressWarnings("unchecked")
    public List<Course> findAllCourses() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Course>) criteria.list();
    }
}
