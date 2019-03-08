package edu.se309.app.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.se309.app.backend.entity.UserStat;
import edu.se309.app.backend.service.interfaces.StatService;

@RestController
@RequestMapping("/api/stats")
public class StatsController extends BaseController<UserStat, Integer, StatService> {

  @Autowired
  public StatsController(StatService statService) {
    super(statService);
  }

  @PutMapping("/incrementStat/{accountId}/{stat}")
  public UserStat incrementStat(@PathVariable("accountId") Integer accountId,
    @PathVariable("stat") String stat) {
    return getService().incrementByOne(accountId, stat);
  }

  @PutMapping("/updateStats/{accountId}")
  public UserStat modifyUserStats(@PathVariable Integer accountId, @RequestBody UserStat userStat) {
    UserStat userStatWithAccount = getService().findByAccountId(accountId);
    userStatWithAccount.copyStats(userStat);
    getService().save(userStatWithAccount);
    return userStatWithAccount;
  }

  @PutMapping("/updateStat/{accountId}/{stat}/{value}")
  public UserStat updateStat(@PathVariable int accountId, String stat, int value) {
    return getService().updateUserStat(accountId, stat, value);
  }
}
