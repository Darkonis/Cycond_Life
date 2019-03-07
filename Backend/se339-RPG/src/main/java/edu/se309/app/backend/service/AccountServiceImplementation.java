package edu.se309.app.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.entity.UserStat;
import edu.se309.app.backend.repository.Interfaces.AccountRepository;
import edu.se309.app.backend.repository.Interfaces.AccountRepositoryCustom;
import edu.se309.app.backend.service.interfaces.AccountService;

@Service
public class AccountServiceImplementation implements AccountService {

	private AccountRepository accountRepository;
	private AccountRepositoryCustom accountRepositoryCustom;
	
	@Autowired
	public AccountServiceImplementation(AccountRepository accountRepository, AccountRepositoryCustom accountRepositoryCustom) {
		this.accountRepository = accountRepository;
		this.accountRepositoryCustom = accountRepositoryCustom;		
	}
	
	@Override
	@Transactional
	public void deleteById(int accountId) {
		accountRepository.deleteById(accountId);
	}

	@Override
	@Transactional
	public List<Account> findAll() {		
		return accountRepository.findAll();
	}

	@Override
	@Transactional
	public Account findByEmail(String email) {
		return accountRepositoryCustom.findByEmail(email);
	}

	@Override
	@Transactional
	public Optional<Account> findById(int accountId) {		
		return accountRepository.findById(accountId);
	}

	@Override
	@Transactional
	public Account findByUsername(String username) {
		return accountRepositoryCustom.findByUsername(username);
	}

	@Override
	@Transactional
	public void save(Account newAccount) {
		accountRepository.save(newAccount);
	}
	
	

}
