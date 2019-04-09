package edu.se309.app.backend.service;

import edu.se309.app.backend.rest.entity.Account;
import edu.se309.app.backend.rest.repository.AccountRepository;
import edu.se309.app.backend.rest.service.AccountServiceImplementation;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplementationTest {

    @InjectMocks
    private AccountServiceImplementation accountService;

    @Mock
    private AccountRepository accountRepository;

    private Account account;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        account = mock(Account.class);
        accountRepository = accountService.getRepository();
    }

    @Test
    void count() {
        long expected = 1L;
        when(accountRepository.count()).thenReturn(expected);
        long actual = accountService.count();
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        doNothing().when(accountRepository).deleteById(anyInt());
        accountService.deleteById(account.getId());
    }

    @Test
    void findAll() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        accounts.add(mock(Account.class));
        when(accountRepository.findAll()).thenReturn(accounts);
        List<Account> newAccounts = accountService.findAll();
        for (Account a : accounts) {
            assertTrue(newAccounts.contains(a));
        }

    }

    @Test
    void findById() {
        int id = account.getId();
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));
        Account newAccount = accountService.findById(id);
        assertEquals(account, newAccount);

    }

    @Test
    void nullCheck() {
        assertThrows(ServiceException.class,
                () -> accountService.nullCheck(Optional.empty(), "Empty Test"));
        assertEquals(account, accountService.nullCheck(Optional.of(account), "Account Test"));
    }

    @Test
    void save() {
        when(accountRepository.save(account)).thenReturn(account);
        accountService.save(account);
    }

    @Test
    void findByEmail() {
        String email = account.getEmail();
        when(accountRepository.findByEmailIgnoreCase(email)).thenReturn(Optional.of(account));
        Account newAccount = accountService.findByEmail(email);
        assertEquals(account, newAccount);
    }

    @Test
    void findByUsername() {
        String username = account.getUsername();
        when(accountRepository.findByUsernameIgnoreCase(username)).thenReturn(Optional.of(account));
        Account newAccount = accountService.findByUsername(username);
        assertEquals(account, newAccount);
    }
}