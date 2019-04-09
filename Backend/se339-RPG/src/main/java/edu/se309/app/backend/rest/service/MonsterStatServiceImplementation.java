package edu.se309.app.backend.rest.service;

import edu.se309.app.backend.rest.entity.MonsterStat;
import edu.se309.app.backend.rest.repository.MonsterStatRepository;
import edu.se309.app.backend.rest.service.interfaces.MonsterStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterStatServiceImplementation
        extends BaseServiceImplementation<MonsterStat, Integer, MonsterStatRepository> implements MonsterStatService {

    @Autowired
    public MonsterStatServiceImplementation(MonsterStatRepository monsterStatRepository) {
        super(monsterStatRepository);
    }
}
