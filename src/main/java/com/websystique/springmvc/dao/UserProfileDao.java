package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Profile;


public interface UserProfileDao {

	List<Profile> findAll();
	
	Profile findByType(String type);
	
	Profile findById(int id);
}
