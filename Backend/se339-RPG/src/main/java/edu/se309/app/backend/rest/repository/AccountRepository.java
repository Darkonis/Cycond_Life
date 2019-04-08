package edu.se309.app.backend.rest.repository;

import edu.se309.app.backend.rest.entity.Account;

import java.util.Optional;

public interface AccountRepository extends BaseRepository<Account, Integer> {

    Optional<Account> findByEmailIgnoreCase(String email);

    Optional<Account> findByUsernameIgnoreCase(String username);
}
