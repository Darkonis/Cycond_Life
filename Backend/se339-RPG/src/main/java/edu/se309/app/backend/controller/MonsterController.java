package edu.se309.app.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.se309.app.backend.entity.Monster;
import edu.se309.app.backend.service.interfaces.MonsterService;
import io.swagger.annotations.ApiOperation;

@RestController

@RequestMapping("/api/monsters")
public class MonsterController extends BaseController<Monster, Integer, MonsterService> {
	@Autowired
	public MonsterController(MonsterService monsterService) {
		super(monsterService);
	}
	
	@PostMapping("/generate")
	public List<Monster> generateMonster() {
		return getService().generateMonsters();
	}

	@ApiOperation(value = "returns first moster's id or -1 if the list is empty")
	@GetMapping("/fistId")
	public int getFirstId() {
		return getService().firstMonsterId();
	}
}
