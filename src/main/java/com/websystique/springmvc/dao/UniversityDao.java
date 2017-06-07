package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.University;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by nicu on 5/20/2017.
 */
@Repository
public class UniversityDao extends AbstractDao<Integer, University> {

    static final Logger logger = LoggerFactory.getLogger(UniversityDao.class);

    /**
     * returns the university by given id
     * @param id
     * @return university by above criteria
     */
    public University findById(Integer id) {
        University university = getByKey(id);
        if (university != null) {
            Hibernate.initialize(university.getStudyYears());
        }
        return university;
    }

    /**
     * returns the university by given short name
     * @param shortName
     * @return university by above criteria
     */
    public University findByShortName(String shortName) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("shortName", shortName));
        University university = (University) crit.uniqueResult();
        if (university != null) {
            Hibernate.initialize(university.getStudyYears());
        }
        return university;
    }

    /**
     * Saves a new university and among its study year object constructed by given number of years.
     * @param university
     */
    public void save(University university) {
        persist(university);
    }
}