package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by nicu on 5/24/2017.
 */
@Repository
public class CourseDao extends AbstractDao<Integer, Course> {

    static final Logger logger = LoggerFactory.getLogger(CourseDao.class);

    /**
     * Finds a course by given id;
     * @param id unique course attribute
     * @return course with the given id
     */
    public Course findById(Integer id) {
        return getByKey(id);
    }

    /**
     * Saves a course into database.
     * @param course
     */
    public void save(Course course) {
        persist(course);
    }

    /**
     * Deletes a course by given id
     * @param id unique course attribute
     */
    public void deleteById(Integer id) {
        Course course = findById(id);
        delete(course);
    }
}
