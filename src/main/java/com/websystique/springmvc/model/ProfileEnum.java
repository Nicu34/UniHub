package com.websystique.springmvc.model;

import java.io.Serializable;

public enum ProfileEnum implements Serializable{
    TEACHER("TEACHER"),
    STUDENT("STUDENT"),
	ADMIN("ADMIN");

	private String userProfileType;

	ProfileEnum(String userProfileType){
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType(){
		return userProfileType;
	}
}
