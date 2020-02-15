package com.starworkflow.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.starworkflow.model.Project;
import com.starworkflow.model.Status;

/**
 * @author Michaił
 *
 */
@Repository
public class ProjectRepository {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

	public Project getProjectByuuid(String uuid) {
		List<Project> projects = em.createQuery("SELECT p FROM Project p WHERE p.uuid = :uuid")
				.setParameter("uuid", uuid).getResultList();
		if (projects.size() != 0) {
			return projects.get(0);
		}
		return null;
	}

	public Status getStatusByuuid(String uuid) {
		List<Status> statues = em.createQuery("SELECT s FROM Status s WHERE s.uuid = :uuid").setParameter("uuid", uuid)
				.getResultList();
		if (statues.size() != 0) {
			return statues.get(0);
		}
		return null;
	}

	@Transactional
	public void changeStatus(boolean finish, boolean skipped, String uuid) {
		em.createQuery("update Status s set s.finish = :finish, s.skipped = :skipped where s.uuid = :uuid")
				.setParameter("uuid", uuid).setParameter("finish", finish).setParameter("skipped", skipped)
				.executeUpdate();
	}

	@Transactional
	public void updateStatusOrder(Status status) {
		em.createQuery("update Status s set s.orderPlace = :orderPlace where s.uuid = :uuid")
				.setParameter("uuid", status.getUuid()).setParameter("orderPlace", status.getOrderPlace())
				.executeUpdate();
	}

	@Transactional
	public void addOrEdit(Project project) {
		em.persist(project);
	}

	@Transactional
	public void addStatus(Status status) {
		em.persist(status);
	}
	@Transactional
	public void deleteStatusByUuid(String uuid) {
		em.createQuery("delete Status s where s.uuid = :uuid")
		.setParameter("uuid", uuid).executeUpdate();
	}
	
	public List<Project> getProjecsByUsername(String username) {
		List<Project> projects = em.createQuery("SELECT p FROM Project p WHERE p.userName = :username")
				.setParameter("username", username).getResultList();
		return projects;
	}

	@Transactional
	public void deleteProject(Project project) {
		em.remove(project);

	}

}
