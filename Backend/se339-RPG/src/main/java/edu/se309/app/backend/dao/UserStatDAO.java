package edu.se309.app.backend.dao;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.entity.UserStat;

public interface UserStatDAO {
	
	public UserStat addStatExperience(Account account, String stat, int amount);
	public UserStat findUserStatByAccount(Account account);
	

}
