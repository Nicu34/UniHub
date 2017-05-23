package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.UserProfileDao;


@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService{
	
	@Autowired
	UserProfileDao dao;
	
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
