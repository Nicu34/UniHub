package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.CourseDao;
import com.websystique.springmvc.model.Course;
import com.websystique.springmvc.model.Teacher;
import com.websystique.springmvc.model.University;
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

    public List<Course> findAll(University university, Teacher teacher) {
        return courseDao.findAllCoursesByUniversityAndTeacher(university, teacher);
    }

    public List<Course> findAll(University university) {
        return courseDao.findAllCourses(university);
    }

    public void deleteById(Integer id) {
        courseDao.deleteById(id);
    }
}