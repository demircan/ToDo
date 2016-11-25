package com.iyzico.api.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iyzico.api.IUserAPI;
import com.iyzico.dao.UserDaoI;
import com.iyzico.domain.User;

@Component
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, 
noRollbackFor = {EmptyResultDataAccessException.class}, rollbackFor = {Exception.class})
public class UserAPI implements IUserAPI {

	@Autowired
	private UserDaoI userDao;

	public void saveUser(User user) {
		userDao.persist(user);
	}

	public void updateUser(User user) {
		userDao.merge(user);
	}

	public void deleteUser(User user) {
		userDao.delete(userDao.findById(user.getId()));
	}

	public void deleteUserById(Long id) {
		userDao.delete(userDao.findById(id));
	}

	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	public User getUserById(Long id) {
		return userDao.findById(id);
	}

	public Optional<User> getUserByName(String username){
		
		Optional<User> user =  userDao.findUserByName(username);
		if( user.isPresent()) {
			return user;
		}
		else
			return Optional.empty();
	}

	@Override
	public Boolean checkExistUsername(String name) {

		if(userDao.checkExistName(name)){
			return true;
		}
		return false;
	}
}
