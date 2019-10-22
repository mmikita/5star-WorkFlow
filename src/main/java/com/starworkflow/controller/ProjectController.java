package com.starworkflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starworkflow.repository.ProjectRepository;

@RestController
public class ProjectController {
	
	@Autowired
	ProjectRepository repo;
	
	



	@GetMapping("/rest")
	public String getEmployeeByID() {
		repo.addProject();
		
		return "hahahahah";
	}
	
	
	
	public ProjectRepository getRepo() {
		return repo;
	}


	public void setRepo(ProjectRepository repo) {
		this.repo = repo;
	}

}
