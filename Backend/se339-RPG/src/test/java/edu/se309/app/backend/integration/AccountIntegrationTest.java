package edu.se309.app.backend.integration;

import edu.se309.app.backend.rest.controller.AccountController;
import edu.se309.app.backend.rest.entity.Account;
import edu.se309.app.backend.rest.service.interfaces.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountIntegrationTest {


    private AccountController accountController;


    private AccountService accountService;

    private Account account;

    @BeforeEach
    public void setup() {
        account = new Account(10318541, "TestUsernameJUNIT", "TestPassword", "TestFirst", "TestLast", "Testing@gmailTest.com", "Admin");
        accountService = accountController.getService();
        accountController = new AccountController(accountService);
    }

    @AfterEach
    public void tearDown() {
        accountController.deleteById(account.getId());
    }

    @Test
    void addAccount() {
        Account newAccount = accountController.addAccount(account);
        assertEquals(account, newAccount);
    }

    @Test
    void findAccountByEmail() {
        accountController.addAccount(account);
        Account newAccount = accountController.findAccountByEmail(account.getEmail());
        assertEquals(account.getEmail(), newAccount.getEmail());
    }

    @Test
    void getAccountByUsername() {
        accountController.addAccount(account);
        Account newAccount = accountController.getAccountByUsername(account.getUsername());
        assertEquals(account.getUsername(), newAccount.getUsername());
    }


    @Test
    public void deleteById() {
        accountController.addAccount(account);
        String expected = "Deleted Account with id: " + account.getId();
        String actual = accountController.deleteById(account.getId());
        assertEquals(expected, actual);
    }


    @Test
    public void findById() {
        accountController.addAccount(account);
        Account newAccount = accountController.findById(account.getId());
        assertEquals(account.getId(), newAccount.getId());
    }
}
