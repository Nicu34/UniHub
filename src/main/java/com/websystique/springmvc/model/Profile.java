package com.websystique.springmvc.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Profile implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	

	@Column(length=15, unique=true, nullable=false)
	private String type = ProfileEnum.ADMIN.getUserProfileType();

	public Profile(String type) {
		this.type = type;
	}

	public Profile() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Profile profile = (Profile) o;

		return type != null ? type.equals(profile.type) : profile.type == null;
	}

	@Override
	public int hashCode() {
		return type != null ? type.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", type=" + type + "]";
	}
}
