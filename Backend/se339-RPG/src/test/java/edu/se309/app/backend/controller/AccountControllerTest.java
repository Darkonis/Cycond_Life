package edu.se309.app.backend.controller;

import edu.se309.app.backend.rest.controller.AccountController;
import edu.se309.app.backend.rest.entity.Account;
import edu.se309.app.backend.rest.service.interfaces.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    private Account account;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        account = mock(Account.class);
        accountService = accountController.getService();
    }

    @Test
    void addAccount() {
        Account newAccount = accountController.addAccount(account);
        assertEquals(account, newAccount);
    }

    @Test
    void findAccountByEmail() {
        when(accountService.findByEmail(account.getEmail())).thenReturn(account);
        Account newAccount = accountController.findAccountByEmail(account.getEmail());
        assertEquals(account.getEmail(), newAccount.getEmail());
    }

    @Test
    void getAccountByUsername() {
        when(accountService.findByUsername(account.getUsername())).thenReturn(account);
        Account newAccount = accountController.getAccountByUsername(account.getUsername());
        assertEquals(account.getUsername(), newAccount.getUsername());
    }


    @Test
    public void count() {
        long expected = 1L;
        when(accountService.count()).thenReturn(expected);
        long count = accountController.count();
        assertEquals(expected, count);
    }


    @Test
    public void deleteById() {
        String expected = "Deleted Account with id: " + account.getId();
        String actual = accountController.deleteById(account.getId());
        assertEquals(expected, actual);
    }


    @Test
    public void findAll() {
        Account account2 = mock(Account.class);
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        accounts.add(account2);
        when(accountService.findAll()).thenReturn(accounts);
        List<Account> newAccounts = accountController.findAll();
        for (Account a : accounts) {
            assertTrue(newAccounts.contains(a));
        }
    }


    @Test
    public void findById() {
        when(accountService.findById(account.getId())).thenReturn(account);
        Account newAccount = accountController.findById(account.getId());
        assertEquals(account.getId(), newAccount.getId());
    }


}
