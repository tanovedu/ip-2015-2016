package org.elsysbg.ip.todo.services;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Singleton
public class EntityManagerService {
	private final EntityManagerFactory emf;
	
	public EntityManagerService() {
		// this should be done only once in the application:
		emf = Persistence.
			createEntityManagerFactory("todolist-jpa");
	}
	
	public EntityManager createEntityManager() {
		return emf.createEntityManager();
	}

}
