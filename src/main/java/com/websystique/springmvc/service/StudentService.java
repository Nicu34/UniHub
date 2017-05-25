package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.StudentDao;
import com.websystique.springmvc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by nicu on 5/24/2017.
 */
@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public Student findById(int id) {
        return studentDao.findById(id);
    }

    public void save(Student student) {
        studentDao.save(student);
    }

    public List<Student> findAll() {
        return studentDao.findAllStudents();
    }

    public void deleteById(Integer id) {
        studentDao.deleteById(id);
    }
}
