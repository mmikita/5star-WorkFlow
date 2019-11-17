package com.starworkflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starworkflow.repository.ProjectRepository;

@RestController
@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200" })
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
