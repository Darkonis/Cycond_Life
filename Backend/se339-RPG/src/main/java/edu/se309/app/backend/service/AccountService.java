package edu.se309.app.backend.service;

import java.util.List;

import edu.se309.app.backend.entity.Account;

public interface AccountService {
	
public List<Account> findAll();
	
	public Account findById(int accountId);
	
	public Account findByUsername(String username);
	
	public Account findByEmail(String email);
	
	public void save(Account newAccount);
	
	public void deleteById(int accountId);

}
