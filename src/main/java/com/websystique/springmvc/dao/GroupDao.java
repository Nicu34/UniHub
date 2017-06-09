package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.SchoolGroup;
import com.websystique.springmvc.model.University;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nicu on 5/24/2017.
 */
@Repository
public class GroupDao extends AbstractDao<Integer, SchoolGroup> {

    static final Logger logger = LoggerFactory.getLogger(GroupDao.class);

    /**
     * Gets school group by given id
     * @param id
     * @return school group with above criteria
     */
    public SchoolGroup findById(Integer id) {
        SchoolGroup schoolGroup = getByKey(id);
        if (schoolGroup != null) {
            Hibernate.initialize(schoolGroup.getStudents());
        }

        return schoolGroup;
    }

    /**
     * Saves the schoolGroup into database.
     * @param schoolGroup
     */
    public void save(SchoolGroup schoolGroup) {
        persist(schoolGroup);
    }


    /**
     * Find all school groups by given university.
     * @param university
     * @return list of groups with above criteria
     */
    @SuppressWarnings("unchecked")
    public List<SchoolGroup> findAllGroups(University university) {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("university", university));
        List<SchoolGroup> schoolGroupList = (List<SchoolGroup>) criteria.list();

        schoolGroupList.forEach(schoolGroup -> Hibernate.initialize(schoolGroup.getStudyYear()));

        return schoolGroupList;
    }

    /**
     * Gets school group by its group number
     * @param groupNumber
     * @return school group with above criteria.
     */
    public SchoolGroup findByGroupNumber(Long groupNumber) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("groupNumber", groupNumber));
        return (SchoolGroup) crit.uniqueResult();
    }
}
