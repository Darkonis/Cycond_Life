package edu.se309.app.backend.rest.repository;

import edu.se309.app.backend.rest.entity.Account;

import java.util.Optional;

/**
 * Account Repository
 */
public interface AccountRepository extends BaseRepository<Account, Integer> {

    /**
     * Find account by email
     * @param email email of requested account
     * @return account
     */
    Optional<Account> findByEmailIgnoreCase(String email);

    /**
     * Find account by username
     * @param username username of requested account
     * @return account
     */
    Optional<Account> findByUsernameIgnoreCase(String username);
}
