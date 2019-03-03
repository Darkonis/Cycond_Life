package edu.se309.app.backend.repository.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.se309.app.backend.entity.UserStat;

public interface UserStatRepository extends JpaRepository<UserStat,Integer>{
	
	UserStat findByAccount(Integer accountId);
	
	

}
