package edu.se309.app.backend.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.se309.app.backend.entity.UserStat;
import edu.se309.app.backend.service.UserStatService;

@RestController
@RequestMapping("/api")
public class UserStatsRestController {
	
	private UserStatService userStatService;
	
	@PutMapping("/updateLocation/{accountId}/{longitude}/{latitude}")	
	public UserStat updateLocation (@PathVariable("accountId") int accountId, @PathVariable("longitude") double longitude, @PathVariable("latitude") double latitude) {
		UserStat userStat = userStatService.updateStatsByLocation(longitude, latitude, accountId);
		if (userStat == null) {
			throw new RuntimeException("Invalid request: accountId not found: " + accountId);
		} else {			
			return userStat;
		}		
			
	}

}
