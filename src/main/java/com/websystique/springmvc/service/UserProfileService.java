package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.ProfileDao;
import com.websystique.springmvc.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserProfileService {
	
	@Autowired
	private ProfileDao dao;

	/**
	 * Returns the profile by its id;
	 * @param id
	 * @return the profile with above criteria
	 */
	public Profile findById(int id) {
		return dao.findById(id);
	}
}
