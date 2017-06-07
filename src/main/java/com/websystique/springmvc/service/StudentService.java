package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.StudentDao;
import com.websystique.springmvc.model.Student;
import com.websystique.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by nicu on 5/24/2017.
 */
@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    /**
     * Finds a student by given id;
     * @param id
     * @return student with above critera
     */
    public Student findById(int id) {
        return studentDao.findById(id);
    }

    /**
     * Finds student by given user;
     * @param user
     * @return student with above criteria.
     */
    public Student findByUser(User user) {
        return studentDao.findByUser(user);
    }

    /**
     * Saves a student into database
     * @param student
     */
    public void save(Student student) {
        studentDao.save(student);
    }
}
