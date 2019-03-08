package edu.se309.app.backend.repository;

import org.springframework.stereotype.Repository;
import edu.se309.app.backend.entity.MonsterStat;

@Repository
public interface MonsterStatRepository extends BaseRepository<MonsterStat, Integer> {}
