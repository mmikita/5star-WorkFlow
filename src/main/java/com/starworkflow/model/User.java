package com.starworkflow.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users") 
public class User {
	
	 @GeneratedValue
	 @Id private Long id;
	 
	 @OneToMany
	 @JoinColumn(name = "user_id")
	 List<Project> projects;
	 
	 
	 
	 
		public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}

}
