package edu.se309.app.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.se309.app.backend.service.UserStatService;

@RestController
@RequestMapping("/api")
public class UserStatsRestController {
	
	private UserStatService userStatService;
	
	@PostMapping("/updateLocation")
	public String updateLocation () {
		//TODO
		return "Stats updated";		
	}

}
