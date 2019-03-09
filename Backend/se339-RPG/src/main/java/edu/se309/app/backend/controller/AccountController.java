package edu.se309.app.backend.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.service.interfaces.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController extends BaseController<Account, Integer, AccountService> {

  @Autowired
  public AccountController(AccountService accountService) {
    super(accountService);
  }

  @PostMapping("/add")
  public Account addAccount(@RequestBody Account account) {
    account.setAccountId(0);
    Date currentDate = new Date();
    account.setCreatedOn(currentDate);
    getService().save(account);
    return account;
  }

  @PostMapping("/findByEmail")
  public Account findAccountByEmail(@RequestBody String email) {
    return getService().findByEmail(email);
  }

  @GetMapping("/findByUsername/{username}")
  public Account getAccountByUsername(@PathVariable String username) {
    return getService().findByUsername(username);
  }
}
