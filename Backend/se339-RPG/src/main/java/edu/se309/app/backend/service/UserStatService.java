package edu.se309.app.backend.service;

import edu.se309.app.backend.entity.UserStat;

public interface UserStatService {
	
	public UserStat updateStatsByLocation(double longitude, double latitude, int accountId);
}
