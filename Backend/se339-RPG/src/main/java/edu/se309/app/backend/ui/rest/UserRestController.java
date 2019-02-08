package edu.se309.app.backend.ui.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
	
	@GetMapping("/users")
	public String getUser() 
	{
		return "This is a test!";
	}
	
	@GetMapping("/hello")
	public String getHello() 
	{
		return "This is a hello!";
	}

	
	
}
