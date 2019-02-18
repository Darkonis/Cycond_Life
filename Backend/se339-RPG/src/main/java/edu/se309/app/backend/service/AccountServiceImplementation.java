package edu.se309.app.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.se309.app.backend.dao.AccountDAO;
import edu.se309.app.backend.entity.Account;

@Service
public class AccountServiceImplementation implements AccountService {

	private AccountDAO accountDAO;
	
	@Autowired
	public AccountServiceImplementation(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	@Override
	@Transactional
	public void deleteById(int accountId) {
		accountDAO.deleteById(accountId);
	}

	@Override
	@Transactional
	public List<Account> findAll() {		
		return accountDAO.findAll();
	}

	@Override
	@Transactional
	public Account findByEmail(String email) {
		return accountDAO.findByEmail(email);
	}

	@Override
	@Transactional
	public Account findById(int accountId) {		
		return accountDAO.findById(accountId);
	}

	@Override
	@Transactional
	public Account findByUsername(String username) {
		return accountDAO.findByUsername(username);
	}

	@Override
	@Transactional
	public void save(Account newAccount) {
		accountDAO.save(newAccount);
	}

}
