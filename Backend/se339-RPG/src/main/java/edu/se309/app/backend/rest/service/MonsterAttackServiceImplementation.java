package edu.se309.app.backend.rest.service;

import edu.se309.app.backend.rest.entity.MonsterAttack;
import edu.se309.app.backend.rest.repository.MonsterAttackRepository;
import edu.se309.app.backend.rest.service.interfaces.MonsterAttackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterAttackServiceImplementation extends
        BaseServiceImplementation<MonsterAttack, Integer, MonsterAttackRepository> implements MonsterAttackService {

    @Autowired
    public MonsterAttackServiceImplementation(MonsterAttackRepository monsterAttackRepository) {
        super(monsterAttackRepository);
    }
}
