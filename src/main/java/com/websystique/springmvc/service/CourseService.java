package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.CourseDao;
import com.websystique.springmvc.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by nicu on 5/24/2017.
 */
@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    /**
     * Finds a course by given id;
     *
     * @param id unique course attribute
     * @return course with the given id
     */
    public Course findById(int id) {
        return courseDao.findById(id);
    }

    /**
     * Saves a course into database.
     *
     * @param course
     */
    public void save(Course course) {
        courseDao.save(course);
    }
}