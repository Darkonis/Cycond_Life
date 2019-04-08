package edu.se309.app.backend.rest.repository;

import edu.se309.app.backend.rest.entity.MonsterStat;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterStatRepository extends BaseRepository<MonsterStat, Integer> {
}
