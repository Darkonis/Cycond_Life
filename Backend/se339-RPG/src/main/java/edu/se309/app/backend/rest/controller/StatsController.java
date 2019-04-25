package edu.se309.app.backend.rest.controller;

import edu.se309.app.backend.rest.entity.Account;
import edu.se309.app.backend.rest.entity.UserStat;
import edu.se309.app.backend.rest.service.interfaces.AccountService;
import edu.se309.app.backend.rest.service.interfaces.BuildingService;
import edu.se309.app.backend.rest.service.interfaces.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Stats Controller
 */
@RestController
@RequestMapping("/api/stats")
public class StatsController extends BaseController<UserStat, Integer, StatService> {

    BuildingService buildingService;
    AccountService accountService;

    /**
     * Constructor for the stats controller
     *
     * @param statService     Stat service associated with controller
     * @param accountService  Account service associated with controller
     * @param buildingService Building service associated with controller
     */
    @Autowired
    public StatsController(StatService statService, AccountService accountService, BuildingService buildingService) {
        super(statService);
        this.accountService = accountService;
        this.buildingService = buildingService;
    }

    /**
     * Calls service to increment stat
     *
     * @param id   id of account
     * @param stat stat to be incremented
     * @return updated stat
     */
    @PutMapping("/incrementStat/{id}/{stat}")
    public UserStat incrementStat(@PathVariable("id") Integer id, @PathVariable("stat") String stat) {
        return getService().incrementByOne(id, stat);
    }

    /**
     * Calls service to update stat
     *
     * @param id       id of account
     * @param userStat updated stat
     * @return updated stat
     */
    @PutMapping("/updateStats/{id}")
    public UserStat modifyUserStats(@PathVariable Integer id, @RequestBody UserStat userStat) {
        UserStat userStatWithAccount = getService().findById(id);
        userStatWithAccount.copyStats(userStat);
        getService().save(userStatWithAccount);
        return userStatWithAccount;
    }

    /**
     * Calls service to update state with given value
     *
     * @param id    id of account
     * @param stat  stat to be updated
     * @param value value to update to
     * @return updated stat
     */
    @PutMapping("/updateStat/{id}/{stat}/{value}")
    public UserStat updateStat(@PathVariable int id, String stat, int value) {
        return getService().updateUserStat(id, stat, value);
    }

    /**
     * Returns stats associated with username
     *
     * @param username username of the account
     * @return stats associated with username
     */
    @GetMapping("/username/{username}")
    public UserStat getStatsByUsername(@PathVariable("username") String username) {
        return getService().getByUsername(username);
    }

    /**
     * Add stats
     *
     * @param userStat Stats to be added
     * @return user stats that were created
     */
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

    /**
     * Updated a user's stats based on location
     *
     * @param id        ID of account
     * @param longitude Longitude position
     * @param latitude  Latitude position
     * @return A string confirming that there was an update or a string saying no stats were updated
     */
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

    /**
     * Return building service
     *
     * @return building service used by the controller
     */
    public BuildingService getBuildingService() {
        return buildingService;
    }

    /**
     * Return Account Service
     *
     * @return account service used by controller
     */
    public AccountService getAccountService() {
        return accountService;
    }
}
