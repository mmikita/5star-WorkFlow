package com.starworkflow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.starworkflow.auth.JwtTokenUtil;
import com.starworkflow.model.Project;
import com.starworkflow.model.User;
import com.starworkflow.service.ProjectService;
import com.starworkflow.service.UserService;

@RestController
@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200" })
public class ProjectController {
	
	@Autowired
	ProjectService service;
	@Autowired
	UserService uService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
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
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	    String token = request.getHeader("Authorization").split(" ")[1];
		jwtTokenUtil.refreshToken(token);

	   User user =  uService.getUserUserName(jwtTokenUtil.getUsernameFromToken(token));
		System.out.println("token: "+token +" userNamefromToken: "+jwtTokenUtil.getUsernameFromToken(token)+" user: "+ user.getLogin());

		
		return "";
	}
	


}
