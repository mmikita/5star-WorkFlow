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
	 List<Project> projects;
	 private String login;
	 private String password;
	private String role;
	 
	 public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	 
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
