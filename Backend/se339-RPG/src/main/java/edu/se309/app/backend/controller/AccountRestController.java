package edu.se309.app.backend.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.service.AccountService;

//Possible TODO refactor error checking. When deleting user, also delete all associated rows from other tables. Currently just deletes account

@RestController
@RequestMapping("/api")
public class AccountRestController {
	
	private AccountService accountService;
	
	@Autowired
	public AccountRestController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PostMapping("/accounts")
	public Account addAccount (@RequestBody Account account) {
		account.setAccountId(0);
		Date currentDate = new Date();
		account.setCreatedOn(currentDate);
		accountService.save(account);
		return account;		
	}
	
	@DeleteMapping("/accounts/{accountID}")
	public String deleteAccount(@PathVariable int accountID) {
		Account toBeDeletedAccount = accountService.findById(accountID);
		if (toBeDeletedAccount == null) {
			throw new RuntimeException("Invalid request: accountId not found: " + accountID);
		} else {	
			accountService.deleteById(accountID);
			return "Deleted Account with Id: " + accountID;
		}		
	}
	
	@GetMapping("/accounts")
	public List<Account> findAll(){
		return accountService.findAll();
	}
	
	@GetMapping("/accounts/getByEmail/{email}")
	public Account getAccountByEmail(@PathVariable String email) {
		Account account = accountService.findByEmail(email);
		if (account == null) {
			throw new RuntimeException("Invalid request: email not found: " + email);
		} else {			
			return account;
		}	
	}
	
	@GetMapping("/accounts/{accountID}")
	public Account getAccountByUsername(@PathVariable int accountID) {
		Account account = accountService.findById(accountID);
		if (account == null) {
			throw new RuntimeException("Invalid request: accountID not found: " + accountID);
		} else {			
			return account;
		}	
	}
	
	@GetMapping("/accounts/getByUsername/{username}")
	public Account getAccountByUsername(@PathVariable String username) {
		Account account = accountService.findByUsername(username);
		if (account == null) {
			throw new RuntimeException("Invalid request: username not found: " + username);
		} else {			
			return account;
		}	
	}
	
	@PutMapping("/accounts")
	public Account updateAccount(@RequestBody Account account) {
		accountService.save(account);
		return account;
	}	

}
