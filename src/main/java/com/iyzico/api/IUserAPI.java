package com.iyzico.api;

import java.util.List;

import com.iyzico.domain.User;

public interface IUserAPI {

	public void saveUser(User user);

	public void updateUser(User user);

	public void deleteUser(User user);

	public void deleteUserById(Long id);

	public List<User> getAllUsers();

	public User getUserById(Long id);

	public User getUserByName(String name);

}
