package edu.se309.app.backend.rest.repository;

import edu.se309.app.backend.rest.entity.UserStat;

import java.util.Optional;

public interface UserStatRepository extends BaseRepository<UserStat, Integer> {

    Optional<UserStat> findByAccountUsername(String username);

}
