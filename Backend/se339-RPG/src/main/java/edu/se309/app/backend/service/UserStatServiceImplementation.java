package edu.se309.app.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.transaction.annotation.Transactional;

import edu.se309.app.backend.dao.UserStatDAO;
import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.entity.UserStat;

public class UserStatServiceImplementation implements UserStatService {

private UserStatDAO userStatDAO;
private BuildingLocationService buildingLocationService;
private AccountService accountService;

	@Autowired
	public UserStatServiceImplementation(UserStatDAO userStatDAO, BuildingLocationService buildingLocationService, AccountService accountService) {
		this.userStatDAO = userStatDAO;		
	}
	
	@Override
	@Transactional
	public UserStat updateStatsByLocation(double longitude, double latitude, int accountId) {
		Point point = new Point(longitude,latitude);		
		String stat = buildingLocationService.findBuildingStat(point);
		Account account = accountService.findById(accountId);
		UserStat userStat = userStatDAO.addStatExperience(account, stat, 1);
		return userStat;
	}


	public BuildingLocationService getBuildingLocationService() {
		return buildingLocationService;
	}
	
	@Autowired
	public void setBuildingLocationService(BuildingLocationService buildingLocationService) {
		this.buildingLocationService = buildingLocationService;
	}

	public AccountService getAccountService() {
		return accountService;
	}
	
	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	

}
