package com.iyzico.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.iyzico.dao.UserDaoI;
import com.iyzico.domain.User;

@Component
public class UserDaoImpl extends CommonDaoImpl<User, Long> implements UserDaoI {

	public Boolean checkExistName(String username) {

		User exampleInstance = new User();
		exampleInstance.setUsername(username);
		List<User> userList = findByExample(exampleInstance);

		if (userList.isEmpty())
			return true;
		else
			return false;
	}

	// TODO check
	public Optional<User> findUserByName(String username) {

		User exampleInstance = new User();
		exampleInstance.setUsername(username);
		List<User> userList = findByExample(exampleInstance);

		if (!userList.isEmpty())
			return Optional.of(userList.get(0));
		else
			return Optional.absent();
	}
	
	

}