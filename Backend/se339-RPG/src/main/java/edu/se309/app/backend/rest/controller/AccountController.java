package edu.se309.app.backend.rest.controller;

import edu.se309.app.backend.rest.entity.Account;
import edu.se309.app.backend.rest.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/accounts")
public class AccountController extends BaseController<Account, Integer, AccountService> {

    /**
     * Account controller constructor
     * @param accountService Account Service associated with controller
     */
    @Autowired
    public AccountController(AccountService accountService) {
        super(accountService);
    }

    /**
     * Add account
     * @param account acccount as a json object
     * @return created account
     */
    @PostMapping("/add")
    public Account addAccount(@RequestBody Account account) {
        account.setId(0);
        Date currentDate = new Date();
        account.setCreatedOn(currentDate);
        getService().save(account);
        return account;
    }

    /**
     * Find acccount by email
     * @param email email of requested account
     * @return account
     */
    @PostMapping("/findByEmail")
    public Account findAccountByEmail(@RequestBody String email) {
        return getService().findByEmail(email);
    }

    /**
     * find account by username
     * @param username username of requested account
     * @return account
     */
    @GetMapping("/findByUsername/{username}")
    public Account getAccountByUsername(@PathVariable String username) {
        return getService().findByUsername(username);
    }
}
