package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.StudyYearDao;
import com.websystique.springmvc.model.StudyYear;
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
public class StudyYearService {

    @Autowired
    private StudyYearDao studyYearDao;

    /**
     * Finds the study year by given id
     * @param id
     * @return study year with above criteria.
     */
    public StudyYear findById(int id) {
        return studyYearDao.findById(id);
    }

    /**
     * Saves study year into database.
     * @param studyYear
     */
    public void save(StudyYear studyYear) {
        studyYearDao.save(studyYear);
    }

    /**
     * Gets list of study year by given university.
     * @param university
     * @return list of study year with above criteria.
     */
    public List<StudyYear> findAll(University university) {
        return studyYearDao.findAllStudyYears(university);
    }

    /**
     * Gets study year by given year and university.
     * @param year
     * @param university
     * @return study year with above criteria
     */
    public StudyYear findByYearAndUniversity(Integer year, University university) {
        return studyYearDao.findByYear(year, university);
    }
}
