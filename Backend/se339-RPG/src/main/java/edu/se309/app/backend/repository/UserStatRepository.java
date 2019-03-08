package edu.se309.app.backend.repository;

import java.util.Optional;
import edu.se309.app.backend.entity.UserStat;

public interface UserStatRepository extends BaseRepository<UserStat, Integer> {

  Optional<UserStat> findByAccountId(int accountId);
}
