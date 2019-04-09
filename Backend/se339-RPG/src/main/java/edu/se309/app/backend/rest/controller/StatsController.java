package edu.se309.app.backend.rest.controller;

import edu.se309.app.backend.rest.entity.Account;
import edu.se309.app.backend.rest.entity.UserStat;
import edu.se309.app.backend.rest.service.interfaces.AccountService;
import edu.se309.app.backend.rest.service.interfaces.BuildingService;
import edu.se309.app.backend.rest.service.interfaces.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/stats")
public class StatsController extends BaseController<UserStat, Integer, StatService> {

    BuildingService buildingService;
    AccountService accountService;

    @Autowired
    public StatsController(StatService statService, AccountService accountService, BuildingService buildingService) {
        super(statService);
        this.accountService = accountService;
        this.buildingService = buildingService;
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

    @PutMapping("/updateStatByLocation/{id}/{longitude}/{latitude}/")
    public String UpdateStatByLocation(@PathVariable Integer id, @PathVariable String longitude, @PathVariable String latitude) {
        String stat = buildingService.findEarnedStatFromLocation(longitude, latitude);
        if (stat.equals("none")) {
            return "No stats were updated based on location";
        }
        UserStat userStat = getService().incrementByOne(id, stat);
        getService().save(userStat);
        return stat + " was updated by one. \n current stats: \n" + userStat.toString();
    }

    public BuildingService getBuildingService() {
        return buildingService;
    }

    public AccountService getAccountService() {
        return accountService;
    }
}
