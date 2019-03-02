package edu.se309.app.backend.repository;

import edu.se309.app.backend.entity.Account;

public interface AccountRepositoryCustom {

 Account findByEmail(String email);	
	
 Account findByUsername(String username);
}
