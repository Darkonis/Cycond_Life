package edu.se309.app.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.se309.app.backend.entity.MonsterAttack;
import edu.se309.app.backend.service.interfaces.MonsterAttackService;

@RestController
@RequestMapping("/api/monsters/attack")
public class MonsterAttackController extends BaseController<MonsterAttack, Integer, MonsterAttackService> {
	@Autowired
	public MonsterAttackController(MonsterAttackService monsterAttackService) {
		super(monsterAttackService);
	}

}
