package com.jsf.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsf.dao.UserRepository;
import com.jsf.model.User;

@Service("userService")
public class UserServiceImpl implements UserService, Serializable {

	private static final long serialVersionUID = 4566445645512541971L;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void saveUser(User user) {
		this.userRepository.saveUser(user);
	}

	@Transactional
	public List<User> listUser() {
		return this.userRepository.listUser();
	}

	@Transactional
	public void deleteUser(User user) {
		this.userRepository.deleteUser(user);
	}

	@Override
	public List<User> getUserListTest() {
		return this.userRepository.getUserListTest();
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
