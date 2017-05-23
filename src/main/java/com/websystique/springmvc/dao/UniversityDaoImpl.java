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
@Repository("universityDao")
public class UniversityDaoImpl extends AbstractDao<Integer, University> implements UniversityDao {

    static final Logger logger = LoggerFactory.getLogger(UniversityDaoImpl.class);

    @Override
    public University findById(int id) {
        University university = getByKey(id);
        if (university != null) {
            Hibernate.initialize(university.getStudyYears());
        }
        return university;
    }

    @Override
    public University findByShortName(String shortName) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("shortName", shortName));
        University university = ((University) criteria.uniqueResult());
        if (university != null) {
            Hibernate.initialize(university.getStudyYears());
        }
        return university;
    }

    @Override
    public void save(University university) {
        persist(university);
    }

    @Override
    public void deleteByShortName(String shortName) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("shortName", shortName));
        University university = ((University) criteria.uniqueResult());
        delete(university);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<University> findAllUniversities() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("shortName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<University>) criteria.list();
    }
}
