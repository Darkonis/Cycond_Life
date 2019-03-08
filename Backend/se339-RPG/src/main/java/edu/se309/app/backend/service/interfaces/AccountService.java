package edu.se309.app.backend.service.interfaces;

import java.util.List;
import java.util.Optional;

import edu.se309.app.backend.entity.Account;

public interface AccountService extends BaseService<Account,Integer> {
	
public void deleteById(int accountId);
	
	List<Account> findAll();
	
	Account findByEmail(String email);
	
	Account findById(int accountId);
	
	Account findByUsername(String username);
	
	void save(Account newAccount);	
	
	long count();
	

}
