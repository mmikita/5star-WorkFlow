package com.starworkflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.starworkflow.model.Project;
import com.starworkflow.service.ProjectService;

@RestController
@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200" })
public class ProjectController {
	
	@Autowired
	ProjectService service;
	
	@PostMapping("/createNew5star")
	public String getEmployeeByID() {
		System.out.print("jestem i--------------------------");
		Project project = service.create5starProject();
		Gson gson = new Gson();
		String starinJson = gson.toJson(project);
		return starinJson;
	}
	
	
	


}
