package edu.se309.app.backend.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.repository.Interfaces.AccountRepository;
import edu.se309.app.backend.service.interfaces.AccountService;

@Service
public class AccountServiceImplementation implements AccountService {

	private AccountRepository accountRepository;
	
	
	@Autowired
	public AccountServiceImplementation(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;			
	}
	
	@Override
	@Transactional
	public void deleteById(int accountId) {
		Account toBeDeletedAccount = findById(accountId);
		accountRepository.deleteById(accountId);
	}

	@Override
	@Transactional
	public List<Account> findAll() {		
		return accountRepository.findAll();
	}

	@Override
	@Transactional
	@NonNull
	public Account findByEmail(String email) {		
		Optional<Account> account = accountRepository.findByEmailIgnoreCase(email);
		return accountNullCheck(account, "Invalid request: No account found with the email: " + email);
	}

	@Override
	@Transactional
	@NonNull
	public Account findById(int accountId) {		
		Optional<Account> account = accountRepository.findById(accountId);
		return accountNullCheck(account, "Invalid request: No account found for id: " + Integer.toString(accountId));
	}

	@Override
	@Transactional
	@NonNull
	public Account findByUsername(String username) {
		username.toLowerCase();
		Optional<Account> account = accountRepository.findByUsernameIgnoreCase(username);
		return accountNullCheck(account, "Invalid request: No account found for user: " + username);
	}

	@Override
	@Transactional
	@NonNull
	public void save(Account newAccount) {
		accountRepository.save(newAccount);
	}
	
	private Account accountNullCheck(Optional<Account> account, String errorMessage) {
		if (account.isEmpty()) {
			throw new ServiceException(errorMessage);
		} else {
			return account.get();
		}
	}
	
	@Override
	@Transactional
	
	public long count() {
		return accountRepository.count();
	}
	
	

}
