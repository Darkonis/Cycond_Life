package edu.se309.app.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.se309.app.backend.entity.MonsterStat;
import edu.se309.app.backend.service.interfaces.MonsterStatService;

@RestController
@RequestMapping("/api/monsters/stat")
public class MonsterStatController extends BaseRestController<MonsterStat, Integer, MonsterStatService> {
	@Autowired
	public MonsterStatController(MonsterStatService monsterStatService) {
		super(monsterStatService);
	}
}
