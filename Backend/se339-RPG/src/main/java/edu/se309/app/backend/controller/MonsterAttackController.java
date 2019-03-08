package edu.se309.app.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.se309.app.backend.entity.MonsterAttack;
import edu.se309.app.backend.repository.interfaces.MonsterAttackRepository;
import edu.se309.app.backend.service.interfaces.MonsterAttackService;

@RestController
@RequestMapping("/api/monster/attack")
public class MonsterAttackController
{
	
	MonsterAttackService monsterAttackService;
	
	@Autowired
	public MonsterAttackController(MonsterAttackService monsterAttackService) {
		this.monsterAttackService = monsterAttackService;
	}	
	
	@GetMapping("/")
	public List<MonsterAttack> findAll()
	{
		return monsterAttackService.findAll();
	}
	
	@GetMapping("/{monsterAttackId}")
	public MonsterAttack findById(@PathVariable int monsterAttackId)
	{
		 return monsterAttackService.findById(monsterAttackId);
	}
}
