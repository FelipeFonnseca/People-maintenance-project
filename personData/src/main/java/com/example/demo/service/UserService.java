package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Users;

public interface UserService {
	/**
	 * The main services that will be used in the controller
	 * @return
	 */
	List<Users> listAll();
	Users listId(String id);
	Users register(Users user);
	Users update(String id, Users user);
	void remove(String id);
}
