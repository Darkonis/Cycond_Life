package edu.se309.app.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.se309.app.backend.entity.UserStat;
import edu.se309.app.backend.service.interfaces.UserStatService;

@RestController
@RequestMapping("/api")
public class UserStatsRestController {
	
	private UserStatService userStatService;	

	@Autowired
	public UserStatsRestController(UserStatService userStatService) {
		this.userStatService = userStatService;		
	}
	
	@GetMapping("/userStats")
	public List<UserStat> findAll(){
		return userStatService.findAll();
	}
	
//	@GetMapping("/userStats/byAccountId/{accountId}")
//	public UserStat getUserStatByAccount (@PathVariable Integer accountId) {		
//		UserStat userStat = userStatService.findStatsByAccount(accountService.findById(accountId).get());
//		if (userStat == null) {
//			throw new RuntimeException("Invalid request: accountId not found: " + accountId);
//		} else {			
//			return userStat;
//		}	
//	}
	
	@GetMapping("/userStats/{statsId}")
	public Optional<UserStat> getAccountByUsername(@PathVariable int statsId) {
		Optional<UserStat> userStat = userStatService.findById(statsId);
		if (userStat == null) {
			throw new RuntimeException("Invalid request: statsId not found: " + statsId);
		} else {			
			return userStat;
		}	
	}
	

}
