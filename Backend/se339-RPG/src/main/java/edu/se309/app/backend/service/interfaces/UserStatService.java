package edu.se309.app.backend.service.interfaces;

import java.util.List;
import java.util.Optional;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.entity.UserStat;

public interface UserStatService {
	
	List<UserStat> findAll();	
	void save(UserStat newUserStat);
	Optional<UserStat> findById(int userStatId);
	

}
