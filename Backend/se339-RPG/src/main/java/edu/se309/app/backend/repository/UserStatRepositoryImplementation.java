package edu.se309.app.backend.repository;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.entity.UserStat;

public class UserStatRepositoryImplementation implements UserStatRepositoryCustom {
	
private EntityManager entityManager;	
	
	@Autowired
	public UserStatRepositoryImplementation(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	

}
