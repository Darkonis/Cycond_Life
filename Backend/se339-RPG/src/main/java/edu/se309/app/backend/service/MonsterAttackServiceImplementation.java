package edu.se309.app.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.se309.app.backend.entity.MonsterAttack;
import edu.se309.app.backend.repository.interfaces.MonsterAttackRepository;
import edu.se309.app.backend.service.interfaces.MonsterAttackService;

@Service
public class MonsterAttackServiceImplementation extends
		BaseServiceImplementation<MonsterAttack, Integer, MonsterAttackRepository> implements MonsterAttackService {

	@Autowired
	public MonsterAttackServiceImplementation(MonsterAttackRepository monsterAttackRepository) {
		super(monsterAttackRepository);
	}

}
