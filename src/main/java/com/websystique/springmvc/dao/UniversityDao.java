package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.University;

import java.util.List;

/**
 * Created by nicu on 5/20/2017.
 */
public interface UniversityDao {
    University findById(int id);

    University findByShortName(String shortName);

    void save(University university);

    void deleteByShortName(String shortName);

    List<University> findAllUniversities();
}
