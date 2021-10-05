package com.example.demo.service.mpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	/**
	 * class that implements the services in the controller
	 */
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<Users> listAll() {
		return this.userRepository.findAll();
	}

	@Override
	public Users listId(String id) {
		return this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Message:User not empty!"));
	}
	
	
	@Override
	public Users register(Users user) {
		return this.userRepository.save(user);
	}
	
	@Override
	public Users update(String id, Users user) {
		return this.userRepository.save(user);
	}

	@Override
	public void remove(String id) {
		this.userRepository.deleteById(id);
	}

}
