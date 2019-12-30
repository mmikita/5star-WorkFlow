package com.starworkflow.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.starworkflow.model.User;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@Repository
public class UserRepository {
	
    static Logger logger = Logger.getLogger(UserRepository.class);	
	@Transactional
	public void addUser(String login, String password) {
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setRole("ROLE_USER_2");
		em.persist(user);
		
	}
	
	@Transactional
	public List<User> getAllUsers() {
        Query q = em.createQuery("select e from User e", User.class);
		return q.getResultList();
		
	}
	
	public User getUserByUsername(String username) {
		List<User> users = em.createQuery("SELECT p FROM project p WHERE p.login = :username").setParameter("username", username).getResultList();

		
		return users.get(0);
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
