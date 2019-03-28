package edu.se309.app.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.repository.AccountRepository;
import edu.se309.app.backend.service.interfaces.AccountService;

@Service
public class AccountServiceImplementation extends BaseServiceImplementation<Account, Integer, AccountRepository>
  implements AccountService {

  @Autowired
  public AccountServiceImplementation(AccountRepository accountRepository) {
    super(accountRepository);
  }

  @Override
  @Transactional
  @NonNull
  public Account findByEmail(String email) {
    Optional<Account> account = getRepository().findByEmailIgnoreCase(email);
    return nullCheck(account, "Invalid request: No account found with the email: " + email);
  }

  @Override
  @Transactional
  @NonNull
  public Account findByUsername(String username) {
    username.toLowerCase();
    Optional<Account> account = getRepository().findByUsernameIgnoreCase(username);
    return nullCheck(account, "Invalid request: No account found for user: " + username);
  }
}
