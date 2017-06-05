package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.University;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nicu on 5/20/2017.
 */
@Repository
public class UniversityDao extends AbstractDao<Integer, University> {

    static final Logger logger = LoggerFactory.getLogger(UniversityDao.class);

    public University findById(Integer id) {
        University university = getByKey(id);
        if (university != null) {
            Hibernate.initialize(university.getStudyYears());
        }
        return university;
    }

    public University findByShortName(String shortName) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("shortName", shortName));
        University university = (University) crit.uniqueResult();
        if (university != null) {
            Hibernate.initialize(university.getStudyYears());
        }
        return university;
    }

    public void save(University university) {
        persist(university);
    }

    public void deleteById(Integer id) {
        University university = findById(id);
        delete(university);
    }

    @SuppressWarnings("unchecked")
    public List<University> findAllUniversities() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("shortName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<University>) criteria.list();
    }
}