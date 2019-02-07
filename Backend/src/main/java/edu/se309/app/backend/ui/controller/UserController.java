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
	public void getUser()
	{
		//Get Request
	}
	
	@PostMapping
	public void createUser()
	{
		//Post Request
	}
	
	@PutMapping
	public void UpdateUser()
	{
		//Put Request
	}
	
	@DeleteMapping
	public void DeleteUser()
	{
		//Delete Request
	}

}
