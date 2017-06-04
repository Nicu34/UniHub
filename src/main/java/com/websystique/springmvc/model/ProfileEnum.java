package com.websystique.springmvc.model;

import java.io.Serializable;

public enum ProfileEnum implements Serializable{
	ADMIN("ADMIN"),
    TEACHER("TEACHER"),
    STUDENT("STUDENT");

	private String userProfileType;

	ProfileEnum(String userProfileType){
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType(){
		return userProfileType;
	}
}
