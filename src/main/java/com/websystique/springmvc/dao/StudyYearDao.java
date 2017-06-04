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

    public StudyYear findById(Integer id) {
        return getByKey(id);
    }

    public void save(StudyYear studyYear) {
        persist(studyYear);
    }

    public void deleteById(Integer id) {
        StudyYear studyYear = findById(id);
        delete(studyYear);
    }

    @SuppressWarnings("unchecked")
    public List<StudyYear> findAllStudyYears(University university) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("university", university));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<StudyYear>) criteria.list();
    }

    public StudyYear findByYear(Integer year, University university) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("year", year));
        crit.add(Restrictions.eq("university", university));
        return (StudyYear) crit.uniqueResult();
    }
}
