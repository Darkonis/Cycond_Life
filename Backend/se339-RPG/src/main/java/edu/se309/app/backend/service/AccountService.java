package edu.se309.app.backend.service;

import java.util.List;

import edu.se309.app.backend.entity.Account;

public interface AccountService {
	
	public void deleteById(int accountId);
	
	public List<Account> findAll();
	
	public Account findByEmail(String email);
	
	public Account findById(int accountId);
	
	public Account findByUsername(String username);
	
	public void save(Account newAccount);

}
