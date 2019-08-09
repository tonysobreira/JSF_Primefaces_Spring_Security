package com.jsf.dao;

import java.util.List;

import com.jsf.model.User;

public interface UserRepository {

	public void saveUser(User user);

	public List<User> listUser();

	public void deleteUser(User user);
	
	public List<User> getUserListTest();
	
	public User findByUsername(String username);
	
}
