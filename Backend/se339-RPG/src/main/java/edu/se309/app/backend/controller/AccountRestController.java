package edu.se309.app.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.se309.app.backend.dao.AccountDAO;
import edu.se309.app.backend.entity.Account;

@RestController
@RequestMapping("/api")
public class AccountRestController {
	
	private AccountDAO accountDAO;
	
	@Autowired
	public AccountRestController(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	@GetMapping("/accounts")
	public List<Account> findAll(){
		return accountDAO.findAll();
	}
	

}
