package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.GroupDao;
import com.websystique.springmvc.model.SchoolGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by nicu on 5/24/2017.
 */
@Service
@Transactional
public class GroupService {

    @Autowired
    private GroupDao courseDao;

    public SchoolGroup findById(int id) {
        return courseDao.findById(id);
    }

    public void save(SchoolGroup course) {
        courseDao.save(course);
    }

    public List<SchoolGroup> findAll() {
        return courseDao.findAllGroups();
    }

    public void deleteById(Integer id) {
        courseDao.deleteById(id);
    }
}
