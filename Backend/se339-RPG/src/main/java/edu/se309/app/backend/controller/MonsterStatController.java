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

import edu.se309.app.backend.entity.MonsterStat;
import edu.se309.app.backend.repository.Interfaces.MonsterStatRepository;


@RestController
@RequestMapping("/api/monster/stat")
public class MonsterStatController 
{
	@Autowired
	MonsterStatRepository monsterStatRepository;
	
	private final Logger logger = LoggerFactory.getLogger(MonsterStatController.class);
	
	@GetMapping("/list")
	public List<MonsterStat> findAll()
	{
		logger.info("Entered into Controller Layer");
		List<MonsterStat>result = monsterStatRepository.findAll();
		logger.info("Number of Records Fetched:" + result.size());
		return result;
	}
	
	@GetMapping("/{monsterStatID}")
	public Optional<MonsterStat> findById(@PathVariable Integer monsterStatID)
	{
		 logger.info("Entered into Controller Layer");
		 Optional<MonsterStat> results = monsterStatRepository.findById(monsterStatID); 
		 logger.info("Number of Records Fetched: 1");
		 return results;
	}
}
