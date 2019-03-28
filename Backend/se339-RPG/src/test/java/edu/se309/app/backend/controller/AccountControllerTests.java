package edu.se309.app.backend.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.repository.AccountRepository;
import edu.se309.app.backend.service.interfaces.AccountService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTests {
	
	@Autowired
	private MockMvc mockController;

	@MockBean
	private AccountRepository accountRepository;
	
	@MockBean
	private AccountService accountService;

	private Account testAccount;
	private Account newAccount;

	@Before
	public void setup() {
		testAccount = mock(Account.class);
		newAccount = mock(Account.class);
	}
	
	@Test
	public void testAccountRepoFindUser() throws Exception {
		when(testAccount.getUsername()).thenReturn("TestUser123"); 
		Assert.assertEquals(testAccount.getUsername(), "TestUser123");
	}

	
	


}
