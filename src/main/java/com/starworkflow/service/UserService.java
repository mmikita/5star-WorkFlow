package com.starworkflow.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.starworkflow.repository.UserRepository;


@Service
public class UserService {
	private static String hiddenPassword = "qwerty";

	static Logger logger = Logger.getLogger(UserService.class);	
	@Autowired
	UserRepository repository;

	public boolean registerUser(Map<String, Object> data) {
		boolean toReturn = false;
		if (data.get("hiddenPassword").equals("qwerty")) {
			String password = (String) data.get("password");
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(password);
			try {
				repository.addUser((String) data.get("username"), encodedPassword);
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
}
