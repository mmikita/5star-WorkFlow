package com.starworkflow.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.starworkflow.repository.UserRepository;

import ch.qos.logback.classic.Logger;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	private static String hiddenPassword = "qwerty";

	public boolean registerUser(Map<String, Object> data) {

		boolean toReturn = false;
//		String hiddenPasswordFromRequest = (String) data.get("hiddenPassword");
		if (data.get("hiddenPassword").equals("qwerty")) {
			String password = (String) data.get("password");
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(password);

			try {
				repository.addUser((String) data.get("username"), encodedPassword);
				toReturn = true;
			} catch (Exception e) {
				System.out.print("Bład------------");
				return false;
			}
		}else {
			
			System.out.print("złe tajne hasło------------");
		
		}
		return toReturn;
	}

}
