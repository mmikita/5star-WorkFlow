package com.starworkflow.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.starworkflow.service.UserService;

@RestController
@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200" })
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping("/register")
	public boolean registerUser(@RequestBody Map<String, Object> data) {

	boolean isRegistered = service.registerUser(data);
		
		return isRegistered;
	}
	
	
}
