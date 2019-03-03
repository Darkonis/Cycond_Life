package edu.se309.app.backend.repository;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.repository.Interfaces.AccountRepositoryCustom;

@Repository
public class AccountRepositoryImplementation implements AccountRepositoryCustom {

	private EntityManager entityManager;
	
	@Autowired
	public AccountRepositoryImplementation(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	

	@Override
	public Account findByEmail(String email) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Account> query = 
				currentSession.createQuery("FROM Account a WHERE a.email = :email", Account.class)
				.setParameter("email", email);
		
		Account accountOutput = query.getSingleResult();
		return accountOutput;
	}

	

	@Override
	public Account findByUsername(String username) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Account> query = 
				currentSession.createQuery("FROM Account a WHERE a.username = :username", Account.class)
				.setParameter("username", username);
		
		Account accountOutput = query.getSingleResult();
		return accountOutput;
	}

	

}
