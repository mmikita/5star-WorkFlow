package com.starworkflow.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;// in play 2.3
import com.google.gson.Gson;
import com.starworkflow.model.Project;
import com.starworkflow.model.Status;
import com.starworkflow.repository.ProjectRepository;

@Service
public class ProjectService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ProjectRepository repo;

	public boolean addOrEditSite(Project project) {
		Project fondProject = repo.getProjectByuuid(project.getUuid());
		if (fondProject == null) {
			logger.info("not fond project with uuid: " + project.getUuid() + " adding new project...");
			for(Status status : project.getStatues()) {
				 status.setId(null);
				repo.addStatus(status);
			}
			repo.addOrEdit(project);
			return true;
		} else {
			logger.info("fond project, updating values...");
			fondProject.setName(project.getName());
			fondProject.setContractNumber(project.getContractNumber());
			fondProject.setURL(project.getURL());
			repo.addOrEdit(fondProject);
		}
		return false;

	}

	public List<Project> getAllProjectsByUserName(String username) {
		List<Project> projects = repo.getProjecsByUsername(username);
		return projects;
	}

	public Project create5starBaseProject() {
		Project project = new Project();
		project.setBaseProject(true);
		List<Status> statuesList = new ArrayList<Status>();
		Status status1 = new Status();
		status1.setName("Krok 1 - tytul");
		status1.setStatusNote("Krok 1 - opis...");
		status1.setOrderPlace(0);
		statuesList.add(status1);

		Status status2 = new Status();
		status2.setName("Krok 2 - tytul");
		status2.setStatusNote("Krok 2 - opis");
		;
		status2.setOrderPlace(1);
		statuesList.add(status2);

	
		project.setStatues(statuesList);
		return project;
	}
	
	public Project create5starProject(String username) {
		Project baseProject = repo.getBaseProjectByUsername(username);
		List<Status> statuesList = baseProject.getStatues();
		  Gson gson = new Gson();
		  Project project = gson.fromJson(gson.toJson(baseProject), Project.class);
		  project.setUuid(UUID.randomUUID().toString());
		  project.setId(null);
		  List<Status> newStatues = new ArrayList<>();
			for(Status status : project.getStatues()) {
				Status newStatus = gson.fromJson(gson.toJson(status), Status.class);
				newStatus.setId(null);
				newStatus.setUuid(null);
				newStatues.add(newStatus);
			}
		project.setStatues(newStatues);
		return project;
	}

	public void deleteProjectByUuid(String uuid) {
		Project project = repo.getProjectByuuid(uuid);
		logger.info("delete project with uuid " + uuid);
		repo.deleteProject(project);
	}
	
	public void deleteStatusByUuid(String uuid, String projectUuid) {
		logger.info("delete status with uuid " + uuid);
		repo.deleteStatusByUuid(uuid);
		Project project = repo.getProjectByuuid(projectUuid);
	     for (int i = 0; i < project.getStatues().size(); i++) {
             project.getStatues().get(i).setOrderPlace(i);
	    	          }
	     repo.addOrEdit(project);
		
	}

	public Project getBaseProject(String username) {

		return repo.getBaseProjectByUsername(username);

	}
	
	public Project getProjectByUUid(String uuid) {
		Project project = repo.getProjectByuuid(uuid);
		Collections.sort(project.getStatues());
		return repo.getProjectByuuid(uuid);

	}

	public void changeStatus(boolean finish, boolean skipped, String uuid) {
		logger.info("updating status project with uuid " + uuid + "  to skipped: " + skipped + "  and finish: " + finish);
		repo.changeStatus(finish, skipped, uuid);
	}
	
	
	public void updateStatusUSerNore(String uuid, String userNote) {
		logger.info("updating status user note, userUUid: " + uuid+" newUserNote: "+userNote);
		repo.updateStatusUserNote(uuid, userNote);
	}

	public void updateOrderPlaces(List<Status> data) {
		for (Status status : data) {
			logger.debug("update status order for status with uuid: " + status.getUuid());
			Status newStatus = repo.getStatusByuuid(status.getUuid());
			newStatus.setOrderPlace(status.getOrderPlace());
			repo.updateStatusOrder(status);
		}

	}
	
	public Status addStatus(String name, String statusNote, String projectUuid) {
		
		Project project = repo.getProjectByuuid(projectUuid);
		Status status = new Status();
		status.setName(name);
		status.setStatusNote(statusNote);
		status.setOrderPlace(project.getStatues().size());
		project.getStatues().add(status);
		repo.addOrEdit(project);
		
		return status;
		
	}

}
