package edu.se309.app.backend.repository;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.entity.UserStat;
import edu.se309.app.backend.repository.Interfaces.UserStatRepositoryCustom;

@Repository
public class UserStatRepositoryImplementation implements UserStatRepositoryCustom {
	
private EntityManager entityManager;	
	
	@Autowired
	public UserStatRepositoryImplementation(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public UserStat findByAccount(Integer account) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<UserStat> query = 
				currentSession.createQuery("FROM UserStat a WHERE a.account = :account", UserStat.class)
				.setParameter("account", account);
		
		UserStat userStatOutput = query.getSingleResult();
		return userStatOutput;
	}
	
	

}
