package edu.se309.app.backend.controller;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import edu.se309.app.backend.entity.Monster;
import edu.se309.app.backend.entity.MonsterStat;
import edu.se309.app.backend.repository.Interfaces.MonsterStatRepository;


@RestController
@RequestMapping("/MonsterStat")
public class MonsterStatController 
{
	@Autowired
	MonsterStatRepository monsterStatRepository;
	
	private final Logger logger = LoggerFactory.getLogger(MonstersController.class);
	
	@GetMapping("/list")
	public List<MonsterStat> findAll()
	{
		logger.info("Entered into Controller Layer");
		List<MonsterStat>result = monsterStatRepository.findAll();
		logger.info("Number of Records Fetched:" + result.size());
		return result;
	}
	
	@GetMapping("/list/{monsterStatId}")
	public MonsterStat findById(@PathVariable("monsterStatId")Integer id)
	{
		 logger.info("Entered into Controller Layer");
		 MonsterStat results = monsterStatRepository.getOne(id); 
		 return results;
	}
}
