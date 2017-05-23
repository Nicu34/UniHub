package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Profile;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, Profile>implements UserProfileDao{

	public Profile findById(int id) {
		return getByKey(id);
	}

	public Profile findByType(String type) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("type", type));
		return (Profile) crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Profile> findAll(){
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("type"));
		return (List<Profile>)crit.list();
	}
	
}
