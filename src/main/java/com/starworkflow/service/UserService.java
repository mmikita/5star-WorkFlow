package com.starworkflow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import com.starworkflow.model.User;
import com.starworkflow.repository.UserRepository;


@Service
public class UserService {

	static Logger logger = Logger.getLogger(UserService.class);	
	@Autowired
	UserRepository repository;

	public boolean registerUser(Map<String, Object> data) {
		String newUsername = (String) data.get("username");
		List<User> users = repository.getAllUsers();
		for(User user: users) {
			if(newUsername.equals(user.getLogin())) {
				logger.info("user already exist");
				return false;
			}
			
		}

		boolean toReturn = false;
		if (data.get("hiddenPassword").equals("qwerty")) {
			String password = (String) data.get("password");
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(password);
			try {
				repository.addUser(newUsername, encodedPassword);
				toReturn = true;
			} catch (Exception e) {
				//System.out.print("Bład------------");
				return false;
			}
		}else {
			logger.info("wrong hidden password");
		}
		return toReturn;
	}
	
	public User getUserUserName(String username) {
		
		return repository.getUserByUsername(username);
		
	}
	
	

	}

