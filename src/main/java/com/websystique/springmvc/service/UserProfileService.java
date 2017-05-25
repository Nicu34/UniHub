package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.ProfileDao;
import com.websystique.springmvc.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserProfileService {
	
	@Autowired
	private ProfileDao dao;
	
	public Profile findById(int id) {
		return dao.findById(id);
	}

	public Profile findByType(String type){
		return dao.findByType(type);
	}

	public List<Profile> findAll() {
		return dao.findAll();
	}
}
