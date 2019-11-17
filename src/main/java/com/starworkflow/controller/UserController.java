package com.starworkflow.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200" })
public class UserController {

	@PostMapping("/register")
	public String getEmployeeByID() {
	System.out.println("-=----------------------------jestem w kontrolerze");
		
		return "huhu";
	}
	
	
}
