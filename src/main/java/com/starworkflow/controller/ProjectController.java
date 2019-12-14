package com.starworkflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;


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
		Project project = service.create5starProject();
		Gson gson = new Gson();
		String starinJson = gson.toJson(project);
		return starinJson;
	}
	
    @ResponseBody
	@PostMapping(path = "/addNew5star",headers = {
    "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addNew5Star(@RequestBody Project project) {
		System.out.println("uuid: "+ project.getUuid() + " name: "+project.getName());
		return "";
	}
	


}
