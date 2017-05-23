package com.websystique.springmvc.service;

import com.websystique.springmvc.model.University;

import java.util.List;

/**
 * Created by nicu on 5/20/2017.
 */
public interface UniversityService {
    University findById(int id);

    University findByShortName(String shortName);

    void saveUniversity(University university, Integer years);

    void updateUniversity(University university);

    void deleteUniversityByShortName(String shortName);

    List<University> findAllUniversities();

    boolean isUniversityShortNameUnique(Integer id, String shortName);
}
