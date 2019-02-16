package edu.se309.app.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.se309.app.backend.entity.Account;

@Repository
public class AccountDAOImplementation implements AccountDAO {

	private EntityManager entityManager;
	
	@Autowired
	public AccountDAOImplementation(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public List<Account> findAll() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Account> query = currentSession.createQuery("from Account", Account.class);
		
		List<Account> accounts = query.getResultList();
		
		return accounts;
	}

}
