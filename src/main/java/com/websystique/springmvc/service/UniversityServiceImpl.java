package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.UniversityDao;
import com.websystique.springmvc.model.StudyYear;
import com.websystique.springmvc.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by nicu on 5/20/2017.
 */
@Service("universityService")
@Transactional
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityDao universityDao;

    @Override
    public University findById(int id) {
        return universityDao.findById(id);
    }

    @Override
    public University findByShortName(String shortName) {
        return universityDao.findByShortName(shortName);
    }

    @Override
    public void saveUniversity(University university, Integer years) {
        university.setStudyYears(buildStudyYearsSet(years));
        universityDao.save(university);
    }


    @Override
    public void updateUniversity(University university) {

    }

    @Override
    public void deleteUniversityByShortName(String shortName) {

    }

    @Override
    public List<University> findAllUniversities() {
        return null;
    }

    @Override
    public boolean isUniversityShortNameUnique(Integer id, String shortName) {
        return false;
    }

    private Set<StudyYear> buildStudyYearsSet(Integer years) {
        return IntStream.range(0, years).mapToObj(StudyYear::new).collect(Collectors.toSet());
    }
}
