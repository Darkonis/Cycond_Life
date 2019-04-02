//package edu.se309.app.backend.service;
//
//import edu.se309.app.backend.controller.AccountController;
//import edu.se309.app.backend.entity.Account;
//import edu.se309.app.backend.repository.AccountRepository;
//import edu.se309.app.backend.service.interfaces.AccountService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//
//@ExtendWith(MockitoExtension.class)
//class AccountServiceImplementationTest {
//
//    @InjectMocks
//    private AccountService accountService;
//
//    @Mock
//    private AccountRepository accountRepository;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        accountService = mock(AccountService.class);
//        accountRepository = accountService.getRepository();
//    }
//
//    @Test
//    void count() {
//    }
//
//    @Test
//    void deleteById() {
//    }
//
//    @Test
//    void findAll() {
//    }
//
//    @Test
//    void findById() {
//    }
//
//    @Test
//    void getRepository() {
//    }
//
//    @Test
//    void nullCheck() {
//    }
//
//    @Test
//    void save() {
//    }
//
//    @Test
//    void findByEmail() {
//    }
//
//    @Test
//    void findByUsername() {
//    }
//}