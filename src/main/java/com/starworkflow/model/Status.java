package com.starworkflow.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Status {
	@GeneratedValue
	@Id
	private Long id;
	private String name;
	private String statusNote;
	private String userNote;
	private boolean finish;
	private int orderPlace;





	public int getOrderPlace() {
		return orderPlace;
	}
	public void setOrderPlace(int orderPlace) {
		this.orderPlace = orderPlace;
	}

	private boolean skipped;
	private String uuid = UUID.randomUUID().toString();
	 public String getUuid() {
		return uuid;
	}
	 public void setUuid(String uuid) {
			this.uuid = uuid;
		}
	

	public boolean isSkipped() {
		return skipped;
	}

	public void setSkipped(boolean skipped) {
		this.skipped = skipped;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusNote() {
		return statusNote;
	}

	public void setStatusNote(String statusNote) {
		this.statusNote = statusNote;
	}

	public String getUserNote() {
		return userNote;
	}

	public void setUserNote(String userNote) {
		this.userNote = userNote;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
