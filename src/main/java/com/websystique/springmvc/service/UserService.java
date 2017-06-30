package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.UserDao;
import com.websystique.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
    private PasswordEncoder passwordEncoder;

	/**
	 * Gets user by given id;
	 * @param id
	 * @return user with above criteria
	 */
	public User findById(int id) {
		return userDao.findById(id);
	}

	/**
	 * Gets user by given ssoid
	 * @param sso
	 * @return user with above criteria.
	 */
	public User findBySSO(String sso) {
		return userDao.findBySSO(sso);
	}

	/**
	 * Saves user into database.
	 * @param user
	 */
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.save(user);
	}

	/**
	 * Gets all users by given university
	 * @return list of users with above criteria
	 */
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	/**
	 * Returns true or false depending if the user given ssoid is unique
	 * @param id
	 * @param sso
	 * @return true / false
	 */
	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = userDao.findOnlyBySSO(sso);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}
}
