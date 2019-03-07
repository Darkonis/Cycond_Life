package edu.se309.app.backend.repository.Interfaces;

import edu.se309.app.backend.entity.Account;

public interface AccountRepositoryCustom {

 Account findByEmail(String email);	
	
 Account findByUsername(String username);
 
 
}


