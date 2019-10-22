package com.starworkflow.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.starworkflow.model.Project;

@Repository
public class ProjectRepository {
	
	@Transactional
	public void addProject() {
		Project project = new Project();
		project.setName("nazwa");
		em.persist(project);
		
	}
	@PersistenceContext
	   private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	     
 
	

}
