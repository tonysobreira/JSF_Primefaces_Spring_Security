package com.jsf.service;

import java.util.List;

import com.jsf.model.User;

public interface UserService {

	public void saveUser(User user);

	public List<User> listUser();

	public void deleteUser(User user);

	public List<User> getUserListTest();

	public User findByUsername(String username);

}
