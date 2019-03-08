package edu.se309.app.backend.repository.interfaces;

import java.util.Optional;
import edu.se309.app.backend.entity.Account;

public interface AccountRepository extends BaseRepository<Account, Integer> {

  Optional<Account> findByEmailIgnoreCase(String email);

  Optional<Account> findByUsernameIgnoreCase(String username);
}
