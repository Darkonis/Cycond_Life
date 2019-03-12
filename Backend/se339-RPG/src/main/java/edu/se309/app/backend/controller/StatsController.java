package edu.se309.app.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.entity.UserStat;
import edu.se309.app.backend.service.interfaces.AccountService;
import edu.se309.app.backend.service.interfaces.StatService;


@RestController
@RequestMapping("/api/stats")
public class StatsController extends BaseController<UserStat, Integer, StatService> {

	AccountService accountService;
	
  @Autowired
  public StatsController(StatService statService, AccountService accountService) {
    super(statService);
    this.accountService = accountService;
  }

  @PutMapping("/incrementStat/{id}/{stat}")
  public UserStat incrementStat(@PathVariable("id") Integer id, @PathVariable("stat") String stat) {
    return getService().incrementByOne(id, stat);
  }

  @PutMapping("/updateStats/{id}")
  public UserStat modifyUserStats(@PathVariable Integer id, @RequestBody UserStat userStat) {
    UserStat userStatWithAccount = getService().findById(id);
    userStatWithAccount.copyStats(userStat);
    getService().save(userStatWithAccount);
    return userStatWithAccount;
  }

  @PutMapping("/updateStat/{id}/{stat}/{value}")
  public UserStat updateStat(@PathVariable int id, String stat, int value) {
    return getService().updateUserStat(id, stat, value);
  }
  
  @GetMapping("/username/{username}")
  public UserStat getStatsByUsername(@PathVariable("username") String username) {
	  return getService().getByUsername(username);
  }
  
  
  @PostMapping("/add")
  public UserStat createStats(@RequestBody UserStat userStat) {
	  Account account = accountService.findById(userStat.getId());
	  UserStat newUserStat = new UserStat();	  
	  newUserStat.copyStats(userStat);
	  newUserStat.setId(userStat.getId());
	  newUserStat.setAccount(account);	  
	  getService().save(newUserStat);
	  return newUserStat;
  }
}
