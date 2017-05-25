package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.StudyYearDao;
import com.websystique.springmvc.model.StudyYear;
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

    public StudyYear findById(int id) {
        return studyYearDao.findById(id);
    }

    public void save(StudyYear studyYear) {
        studyYearDao.save(studyYear);
    }

    public List<StudyYear> findAll() {
        return studyYearDao.findAllStudyYears();
    }

    public void deleteById(Integer id) {
        studyYearDao.deleteById(id);
    }

    public StudyYear findByYear(Integer year) {
        return studyYearDao.findByYear(year);
    }
}
