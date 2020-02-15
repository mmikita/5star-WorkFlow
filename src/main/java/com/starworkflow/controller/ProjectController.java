package com.starworkflow.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.starworkflow.auth.JwtTokenUtil;
import com.starworkflow.model.Project;
import com.starworkflow.model.Status;
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
	
	  @Autowired
	  private UserDetailsService jwtInMemoryUserDetailsService;
	
	@PostMapping("/projects/createNew5star")
	public String getEmployeeByID() {
		Project project = service.create5starProject();
		Gson gson = new Gson();
		String starinJson = gson.toJson(project);
		return starinJson;
	}
	
    @ResponseBody
	@PostMapping(path = "/projects/addNew5star",headers = {
    "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean addNew5Star(@RequestBody(required=false) Project project) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	    String token = request.getHeader("Authorization").split(" ")[1];
		jwtTokenUtil.refreshToken(token);
		return service.addOrEditSite(project);
	}
    
    
    @ResponseBody
	@PostMapping(path = "/projects/getProjectsByUser",headers = {
    "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Project> getAllProjectsByUser(@RequestBody User user) {
    	
        final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(user.getLogin());

    	List<Project> projects = service.getAllProjectsByUserName(user.getLogin());
		return projects;
	}
    
    @ResponseBody
 	@PostMapping(path = "/projects/getProject",headers = {
     "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE)
 	public Project getProjectByUuuid(@RequestBody Map<String, String> data) {
    	return service.getProjectByUUid(data.get("uuid"));
 	}
    
    //toDoBoolean
	@PostMapping("/projects/deleteProject")
	public boolean deleteProject(@RequestBody Map<String, String> data) {
    service.deleteProjectByUuid(data.get("uuid"));
		return true;
	}
	
	@PostMapping("/projects/addStatus")
	public boolean addStatus(@RequestBody Map<String, String> data) {
    
		
		return true;
	}
	
    @RequestMapping(value = "/projects/updateOrderPlaces", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean updateOrderPlaces(@RequestBody List<Status> data) {
    
service.updateOrderPlaces(data);
		return true;
	}
    
	
    //toDoBoolean
	@PostMapping("/projects/changeStatus")
	public boolean updateStatus(@RequestBody Map<String, Object> data) {
		service.changeStatus((boolean)data.get("finish"), (boolean)data.get("skipped"), (String)data.get("uuid"));
		return true;
	}
    
    
    
    
    
	


}
