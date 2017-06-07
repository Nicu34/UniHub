package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.GroupDao;
import com.websystique.springmvc.model.SchoolGroup;
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
public class GroupService {

    @Autowired
    private GroupDao groupDao;

    /**
     * Gets school group by given id
     * @param id
     * @return school group with above criteria
     */
    public SchoolGroup findById(int id) {
        return groupDao.findById(id);
    }

    /**
     * Gets school group by its group number
     * @param groupNumber
     * @return school group with above criteria.
     */
    public SchoolGroup findByGroupNumber(Long groupNumber) {
        return groupDao.findByGroupNumber(groupNumber);
    }

    /**
     * Saves the schoolGroup into database.
     * @param schoolGroup
     */
    public void save(SchoolGroup schoolGroup) {
        groupDao.save(schoolGroup);
    }

    /**
     * Find all school groups by given university.
     * @param university
     * @return list of groups with above criteria
     */
    public List<SchoolGroup> findAll(University university) {
        return groupDao.findAllGroups(university);
    }
}
