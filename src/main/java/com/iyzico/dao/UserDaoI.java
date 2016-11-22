package com.iyzico.dao;

import com.google.common.base.Optional;
import com.iyzico.domain.User;



public interface UserDaoI extends CommonDaoI<User, Long> {

	public Boolean checkExistName(String name);
	
	public Optional<User> findUserByName(String name);
}
