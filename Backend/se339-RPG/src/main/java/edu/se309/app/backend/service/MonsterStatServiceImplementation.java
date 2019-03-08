package edu.se309.app.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.se309.app.backend.entity.MonsterStat;
import edu.se309.app.backend.repository.MonsterStatRepository;
import edu.se309.app.backend.service.interfaces.MonsterStatService;

@Service
public class MonsterStatServiceImplementation
  extends BaseServiceImplementation<MonsterStat, Integer, MonsterStatRepository>
  implements MonsterStatService {

  @Autowired
  public MonsterStatServiceImplementation(MonsterStatRepository monsterStatRepository) {
    super(monsterStatRepository);
  }
}
