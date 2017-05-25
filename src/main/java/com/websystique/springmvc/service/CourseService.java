package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.CourseDao;
import com.websystique.springmvc.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by nicu on 5/24/2017.
 */
@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    public Course findById(int id) {
        return courseDao.findById(id);
    }

    public void save(Course course) {
        courseDao.save(course);
    }

    public List<Course> findAll() {
        return courseDao.findAllCourses();
    }

    public void deleteById(Integer id) {
        courseDao.deleteById(id);
    }
}