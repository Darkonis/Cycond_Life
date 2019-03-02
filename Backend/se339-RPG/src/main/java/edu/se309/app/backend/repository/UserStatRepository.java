package edu.se309.app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.entity.UserStat;

public interface UserStatRepository extends JpaRepository<UserStat,Integer>{
	
	

}
