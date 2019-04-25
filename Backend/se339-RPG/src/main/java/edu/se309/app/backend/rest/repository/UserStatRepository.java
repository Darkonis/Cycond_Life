package edu.se309.app.backend.rest.repository;

import edu.se309.app.backend.rest.entity.UserStat;

import java.util.Optional;

/**
 * User Stat Repository
 */
public interface UserStatRepository extends BaseRepository<UserStat, Integer> {

    /**
     * Find user's stats by account username
     * @param username username of account
     * @return stats
     */
    Optional<UserStat> findByAccountUsername(String username);

}
