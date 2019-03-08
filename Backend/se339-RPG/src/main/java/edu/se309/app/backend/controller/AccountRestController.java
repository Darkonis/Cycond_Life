package edu.se309.app.backend.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.service.interfaces.AccountService;

//Possible TODO refactor error checking. When deleting user, also delete all associated rows from other tables. Currently just deletes account

@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {
	
	private AccountService accountService;	
	
	@Autowired
	public AccountRestController(AccountService accountService) {
		this.accountService = accountService;		
	}
	
	@PostMapping("/add")
	public Account addAccount (@RequestBody Account account) {
		account.setAccountId(0);
		Date currentDate = new Date();
		account.setCreatedOn(currentDate);
		account.getUserStat().setStatsId(0);
		account.getUserStat().setAccount(account);		
		accountService.save(account);
		return account;		
	}
	
	@DeleteMapping("/{accountId}")
	public String deleteAccount(@PathVariable int accountId) {		
		return "Deleted Account with Id: " + accountId;
	}		
	
	
	@GetMapping("/")
	public List<Account> findAll(){
		return accountService.findAll();
	}
	
	@GetMapping("/findByEmail")
	public Account findAccountByEmail(@RequestBody String email) {
		return accountService.findByEmail(email);
		
	}
	
	@GetMapping("/{accountId}")
	public Account getAccountById(@PathVariable int accountId) {
		return accountService.findById(accountId);
			
	}
	
	@GetMapping("/findByUsername/{username}")
	public Account getAccountByUsername(@PathVariable String username) {
		return accountService.findByUsername(username);
		
	}
	
	@GetMapping("/count")
	public long count(){
		return accountService.count();
	}
	
	
		
		
	

}
