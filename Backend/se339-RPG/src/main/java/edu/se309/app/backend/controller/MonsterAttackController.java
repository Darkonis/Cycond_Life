package edu.se309.app.backend.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import edu.se309.app.backend.entity.MonsterAttack;
import edu.se309.app.backend.repository.Interfaces.MonsterAttackRepository;

@RestController
@RequestMapping("/api/monster/attack")
public class MonsterAttackController
{
	@Autowired
	MonsterAttackRepository monsterAttackRepository;
	
	private final Logger logger = LoggerFactory.getLogger(MonsterAttackController.class);
	
	@GetMapping("/list")
	public List<MonsterAttack> findAll()
	{
		logger.info("Entered into Controller Layer");
		List<MonsterAttack>result = monsterAttackRepository.findAll();
		logger.info("Number of Records Fetched:" + result.size());
		return result;
	}
	
	@GetMapping("/{monsterAttackID}")
	public Optional<MonsterAttack> findById(@PathVariable Integer monsterAttackID)
	{
		 logger.info("Entered into Controller Layer");
		 Optional<MonsterAttack> results = monsterAttackRepository.findById(monsterAttackID); 
		 logger.info("Number of Records Fetched: 1");
		 return results;
	}
}
