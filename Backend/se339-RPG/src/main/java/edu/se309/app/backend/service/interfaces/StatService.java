package edu.se309.app.backend.service.interfaces;

import java.util.List;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.entity.UserStat;

public interface StatService {
	
	List<UserStat> findAll();	
	void save(UserStat newUserStat);	
	UserStat updateUserStat(int accountId, String stat, int value);	
	UserStat incrementByAmount(int accountId, String stat, int amount);
	UserStat incrementByOne(int accountId, String stat);
	UserStat findByAccountId(int accountId);
	long count();

}
