package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.StudyYear;
import com.websystique.springmvc.model.University;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nicu on 5/24/2017.
 */
@Repository
public class StudyYearDao extends AbstractDao<Integer, StudyYear> {

    static final Logger logger = LoggerFactory.getLogger(StudyYearDao.class);

    /**
     * Finds the study year by given id
     * @param id
     * @return study year with above criteria.
     */
    public StudyYear findById(Integer id) {
        return getByKey(id);
    }

    /**
     * Saves study year into database.
     * @param studyYear
     */
    public void save(StudyYear studyYear) {
        persist(studyYear);
    }

    /**
     * Gets list of study year by given university.
     * @param university
     * @return list of study year with above criteria.
     */
    @SuppressWarnings("unchecked")
    public List<StudyYear> findAllStudyYears(University university) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("university", university));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<StudyYear>) criteria.list();
    }

    /**
     * Gets study year by given year and university.
     * @param year
     * @param university
     * @return study year with above criteria
     */
    public StudyYear findByYear(Integer year, University university) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("year", year));
        crit.add(Restrictions.eq("university", university));
        return (StudyYear) crit.uniqueResult();
    }
}
