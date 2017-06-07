package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Profile;
import org.springframework.stereotype.Repository;


@Repository
public class ProfileDao extends AbstractDao<Integer, Profile> {

    /**
     * Returns the profile by its id;
     * @param id
     * @return the profile with above criteria
     */
    public Profile findById(int id) {
        return getByKey(id);
    }
}
