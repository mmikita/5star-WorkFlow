package com.starworkflow.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200" })
public class UserController {

	@PostMapping("/register")
	public String getEmployeeByID(@RequestBody Map<String, Object> payload) {
	System.out.println("-=----------------------------jestem w kontrolerze: " + payload);
		
		return "huhu";
	}
	
	
}
