package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Profile;


public interface UserProfileService {

	Profile findById(int id);

	Profile findByType(String type);
	
	List<Profile> findAll();
	
}
