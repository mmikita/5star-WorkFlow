package com.starworkflow.model;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {
	
	 @GeneratedValue
	 @Id private Long id;
	 private String name;
	 private String contractNumber;
	 private String URL;


	 public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="Project_ID")
	 private List<Status> statues;
	 
	 private String userName;
	 public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private String uuid = UUID.randomUUID().toString();
	 public String getUuid() {
		return uuid;
	}

	public int hashCode() {
		 return Objects.hash(uuid);
	 }
	 
	 public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean equals(Object that) {
		 return this == that | that instanceof Project
				 && Objects.equals(uuid, ((Project)that).uuid);
		 
	 }
	 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Status> getStatues() {
		return statues;
	}

	public void setStatues(List<Status> statues) {
		this.statues = statues;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;		
	}
	
}
