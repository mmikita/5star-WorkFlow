package com.starworkflow.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;// in play 2.3
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
		
	if(fondProject==null) {
		logger.info("not fond project with uuid: "+project.getUuid()+" adding new project...");
		repo.addOrEdit(project);
		return true;
	}else {
		logger.info("fond project, updating values...");
		fondProject.setName(project.getName());
		fondProject.setContractNumber(project.getContractNumber());
		fondProject.setURL(project.getURL());
		repo.addOrEdit(fondProject);
	}
	return false;
		
	}
	
	public List<Project> getAllProjectsByUserName(String username){
		List<Project> projects = repo.getProjecsByUsername(username);
		return projects;
	}
	public Project create5starProject() {

		Project project = new Project();		
		List<Status> statuesList = new ArrayList<Status>();
		
		Status status1 = new Status();
		status1.setName("instalacja strony");
		status1.setStatusNote("Paczki instalacyjne: link");
		status1.setOrderPlace(0);
		statuesList.add(status1);
		
		Status status2 = new Status();
		status2.setName("Aktualizacja");
		status2.setStatusNote("Link do aktualizacji: link");;
		status2.setOrderPlace(1);
		statuesList.add(status2);
		
		Status status3 = new Status();
		status3.setName("Zamiana zdjec");
		status3.setStatusNote("Odrazu wyczyscic niepotrzebne pliki z imgaes/design");
		status3.setOrderPlace(2);
		statuesList.add(status3);
		
		Status status4 = new Status();
		status4.setName("Ustawienia globalne");
		status4.setStatusNote("Font, przyciski, itp itd");
		status4.setOrderPlace(3);
		statuesList.add(status4);
		
		Status status5 = new Status();
		status5.setName("Prace nad strona glowna");
		status5.setStatusNote("slider, boxy, menu");
		status5.setOrderPlace(4);
		Status status51 = new Status();
		status51.setName("Prace nad menu");
		status51.setStatusNote("ojojo podzakladki");
		statuesList.add(status5);
		
		Status status6 = new Status();
		status6.setName("Prace nad strona kontakt");
		status6.setStatusNote("formularz");
		status6.setOrderPlace(5);
		statuesList.add(status6);
		
		Status status7 = new Status();
		status7.setName("Podstrony");
		status7.setStatusNote("formularz");
		status7.setOrderPlace(6);
		statuesList.add(status7);
		
		Status status8 = new Status();
		status8.setName("Prace końcowe");
		status8.setStatusNote("ustaw JCH");
		status8.setOrderPlace(7);
		statuesList.add(status8);
		
		project.setStatues(statuesList);
		
		return project;
	}
	
public void deleteProjectByUuid(String uuid) {
	Project project = repo.getProjectByuuid(uuid);
	logger.info("delete project with uuid "+uuid);
	repo.deleteProject(project);
	}

public Project getProjectByUUid(String uuid) {
	Project project = repo.getProjectByuuid(uuid);
	
	Collections.sort(project.getStatues());	
	
	return repo.getProjectByuuid(uuid);

}

public void changeStatus(boolean finish, boolean skipped, String uuid) {
	logger.info("updating status project with uuid "+uuid+"  to skipped: "+skipped +"  and finish: "+finish);
	repo.changeStatus(finish, skipped, uuid);
}

public void updateOrderPlaces(List<Status> data) {
	for(Status status: data) {
		logger.debug("update status order for status with uuid: "+ status.getUuid());
	Status newStatus = repo.getStatusByuuid(status.getUuid());
	newStatus.setOrderPlace(status.getOrderPlace());
repo.updateStatusOrder(status);
}


  



	

}



}
