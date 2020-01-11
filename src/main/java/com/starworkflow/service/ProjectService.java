package com.starworkflow.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		statuesList.add(status1);
		
		Status status2 = new Status();
		status2.setName("Aktualizacja");
		status2.setStatusNote("Link do aktualizacji: link");;
		statuesList.add(status2);
		
		Status status3 = new Status();
		status3.setName("Zamiana zdjec");
		status3.setStatusNote("Odrazu wyczyscic niepotrzebne pliki z imgaes/design");
		statuesList.add(status3);
		
		Status status4 = new Status();
		status4.setName("Ustawienia globalne");
		status4.setStatusNote("Font, przyciski, itp itd");
		statuesList.add(status4);
		
		Status status5 = new Status();
		status5.setName("Prace nad strona glowna");
		status5.setStatusNote("slider, boxy, menu");
		Status status51 = new Status();
		status51.setName("Prace nad menu");
		status51.setStatusNote("ojojo podzakladki");
		statuesList.add(status5);
		
		Status status6 = new Status();
		status6.setName("Prace nad strona kontakt");
		status6.setStatusNote("formularz");
		statuesList.add(status6);
		
		Status status7 = new Status();
		status7.setName("Podstrony");
		status7.setStatusNote("formularz");
		statuesList.add(status7);
		
		Status status8 = new Status();
		status8.setName("Prace końcowe");
		status8.setStatusNote("ustaw JCH");
		statuesList.add(status8);
		
		project.setStatues(statuesList);
		
		return project;
	}

}
