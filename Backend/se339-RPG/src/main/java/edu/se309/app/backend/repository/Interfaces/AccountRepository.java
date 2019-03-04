package edu.se309.app.backend.repository.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.se309.app.backend.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Integer> {	
	
}
