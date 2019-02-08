package edu.se339.app.backend.ui.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
	
	@GetMapping("/")
	public String getUser() 
	{
		return "This is a test!";
	}

}
