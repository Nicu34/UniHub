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
@Service
@Transactional
public class UniversityService {

    @Autowired
    private UniversityDao universityDao;

    public University findById(int id) {
        return universityDao.findById(id);
    }

    public void save(University university, Integer years) {
        university.setStudyYears(buildStudyYearsSet(years));
        universityDao.save(university);
    }

    public List<University> findAll() {
        return universityDao.findAllUniversities();
    }

    public void deleteById(Integer id) {
        universityDao.deleteById(id);
    }

    private Set<StudyYear> buildStudyYearsSet(Integer years) {
        return IntStream.range(0, years)
                .mapToObj(StudyYear::new)
                .collect(Collectors.toSet());
    }
}
