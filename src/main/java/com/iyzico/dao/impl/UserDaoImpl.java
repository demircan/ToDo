package com.iyzico.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.iyzico.dao.UserDaoI;
import com.iyzico.domain.User;

@Component
public class UserDaoImpl extends CommonDaoImpl<User, Long> implements UserDaoI {

	public Boolean checkExistName(String username) {

		User exampleInstance = new User();
		exampleInstance.setUsername(username);
		List<User> userList = findByExample(exampleInstance);

		if (!userList.isEmpty())
			return true;
		else
			return false;
	}

	public Optional<User> findUserByName(String username) {

		User exampleInstance = new User();
		exampleInstance.setUsername(username);
		List<User> userList = findByExample(exampleInstance);

		if (!userList.isEmpty())
			return Optional.of(userList.get(0));
		else
			return null;
	}
}