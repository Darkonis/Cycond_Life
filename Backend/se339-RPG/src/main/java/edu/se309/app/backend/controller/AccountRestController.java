package edu.se309.app.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountRestController {
	
	private AccountService accountService;
	
	@Autowired
	public AccountRestController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@GetMapping("/accounts")
	public List<Account> findAll(){
		return accountService.findAll();
	}
	

}
