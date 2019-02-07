package edu.se309.app.backend.ui.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("users")
public class UserController {
	
	@GetMapping
	public String getUser()
	{
		return "get user request";
	}
	
	@PostMapping
	public String createUser()
	{
		return "post user request";
	}
	
	@PutMapping
	public String UpdateUser()
	{
		return "put user request";
	}
	
	@DeleteMapping
	public String DeleteUser()
	{
		return "delete user request";
	}

}
