package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.UniversityDao;
import com.websystique.springmvc.model.StudyYear;
import com.websystique.springmvc.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by nicu on 5/20/2017.
 */
@Service
@Transactional
public class UniversityService {

    @Autowired
    private UniversityDao universityDao;
    /**
     * returns the university by given id
     * @param id
     * @return university by above criteria
     */
    public University findById(int id) {
        return universityDao.findById(id);
    }

    /**
     * returns the university by given short name
     * @param shortName
     * @return university by above criteria
     */
    public University findByShortName(String shortName) {
        return universityDao.findByShortName(shortName);
    }

    /**
     * Saves a new university and among its study year object constructed by given number of years.
     * @param university
     * @param years
     */
    public void save(University university, Integer years) {
        university.setStudyYears(buildStudyYearsSet(years, university));
        universityDao.save(university);
    }

    /**
     * builds the List of study years by given university and years
     * @param years
     * @param university
     * @return List of study year
     */
    private List<StudyYear> buildStudyYearsSet(Integer years, University university) {
        return IntStream.range(1, years + 1)
                .mapToObj(value -> this.buildStudyYear(value, university))
                .collect(Collectors.toList());
    }

    /**
     * builds study year object by given year and university
     * @param year
     * @param university
     * @return study year object
     */
    private StudyYear buildStudyYear(Integer year, University university) {
        StudyYear studyYear = new StudyYear();

        studyYear.setYear(year);
        studyYear.setUniversity(university);

        return studyYear;
    }
}
