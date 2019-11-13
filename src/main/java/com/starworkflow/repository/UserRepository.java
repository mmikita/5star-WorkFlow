package com.starworkflow.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.starworkflow.model.User;

@Repository
public class UserRepository {
	

	
	@Transactional
	public void addUser() {
		User user = new User();
		user.setLogin("in28minutes");
		user.setPassword("$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e");
		user.setRole("ROLE_USER_2");
		em.persist(user);
		
	}
	
	@Transactional
	public List<User> getAllUsers() {
        Query q = em.createQuery("select e from User e", User.class);

		
		return q.getResultList();
	
		
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
