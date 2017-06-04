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

    public SchoolGroup findById(Integer id) {
        SchoolGroup schoolGroup = getByKey(id);
        if (schoolGroup != null) {
            Hibernate.initialize(schoolGroup.getStudents());
        }

        return schoolGroup;
    }

    public void save(SchoolGroup schoolGroup) {
        persist(schoolGroup);
    }

    public void deleteById(Integer id) {
        SchoolGroup schoolGroup = findById(id);
        delete(schoolGroup);
    }

    @SuppressWarnings("unchecked")
    public List<SchoolGroup> findAllGroups(University university) {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("university", university));
        List<SchoolGroup> schoolGroupList = (List<SchoolGroup>) criteria.list();

        schoolGroupList.forEach(schoolGroup -> Hibernate.initialize(schoolGroup.getStudyYear()));

        return schoolGroupList;

    }
}
