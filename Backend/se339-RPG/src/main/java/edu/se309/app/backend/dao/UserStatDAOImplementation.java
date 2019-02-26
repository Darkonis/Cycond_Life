package edu.se309.app.backend.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.entity.UserStat;

public class UserStatDAOImplementation implements UserStatDAO {
	
private EntityManager entityManager;	
	
	@Autowired
	public UserStatDAOImplementation(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public UserStat addStatExperience(Account account, String stat, int amount) {
		Session currentSession = entityManager.unwrap(Session.class);		
		Query<UserStat> query = 
				currentSession.createQuery("Update UserStat SET :stat = :stat + :amount =WHERE account = :acount", UserStat.class)
				.setParameter("stat", stat)
				.setParameter("amount", amount)			
				.setParameter("account", account);
		UserStat userStat = query.getSingleResult();		
		currentSession.saveOrUpdate(userStat);	
		return userStat;			
	}
	
	@Override
	public UserStat findUserStatByAccount(Account account) {
		Session currentSession = entityManager.unwrap(Session.class);		
		Query<UserStat> query = 
				currentSession.createQuery("FROM UserStat a WHERE account = :account", UserStat.class)
				.setParameter("account", account);		
		UserStat userStatOutput = query.getSingleResult();
		return userStatOutput;
		
	}

}
