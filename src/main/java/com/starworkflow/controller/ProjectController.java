package com.starworkflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {
	
	
	@GetMapping("/rest")
	public String getEmployeeByID() {
		return "hahahahah";
	}

}
