package com.starworkflow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.starworkflow.model.Project;
import com.starworkflow.model.Status;



@Service
public class ProjectService {
	
	public boolean addOrEditSite(Project project) {
		
		
		
		return false;
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
		status5.setStatus(status51);
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
