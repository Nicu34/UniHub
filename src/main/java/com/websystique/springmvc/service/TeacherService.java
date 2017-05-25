package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.TeacherDao;
import com.websystique.springmvc.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by nicu on 5/24/2017.
 */
@Service
@Transactional
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    public Teacher findById(int id) {
        return teacherDao.findById(id);
    }

    public void save(Teacher teacher) {
        teacherDao.save(teacher);
    }

    public List<Teacher> findAll() {
        return teacherDao.findAllTeachers();
    }

    public void deleteById(Integer id) {
        teacherDao.deleteById(id);
    }
}
