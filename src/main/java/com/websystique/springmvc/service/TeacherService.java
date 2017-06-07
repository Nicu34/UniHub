package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.TeacherDao;
import com.websystique.springmvc.model.Teacher;
import com.websystique.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by nicu on 5/24/2017.
 */
@Service
@Transactional
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    /**
     * Gets teacher by given id
     * @param id
     * @return teacher with above criteria.
     */
    public Teacher findById(int id) {
        return teacherDao.findById(id);
    }

    /**
     * Gets teacher by given user
     * @param user
     * @return teacher with above criteria.
     */
    public Teacher findByUser(User user) {
        return teacherDao.findByUser(user);
    }

    /**
     * Saves a teacher into database.
     * @param teacher
     */
    public void save(Teacher teacher) {
        teacherDao.save(teacher);
    }
}
