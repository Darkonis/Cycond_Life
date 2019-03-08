package edu.se309.app.backend.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.se309.app.backend.entity.MonsterStat;
import edu.se309.app.backend.service.interfaces.MonsterStatService;


@RestController
@RequestMapping("/api/monsters/stat")
public class MonsterStatController 
{
	
	private MonsterStatService monsterStatService;
	
	@Autowired
	public MonsterStatController(MonsterStatService monsterStatService) {
		this.monsterStatService = monsterStatService;
	}	
	
	@GetMapping("/")
	public List<MonsterStat> findAll()
	{
		return monsterStatService.findAll();
	}
	
	@GetMapping("/{monsterStatID}")
	public MonsterStat findById(@PathVariable int monsterStatID)
	{
		 return monsterStatService.findById(monsterStatID);
	}
}
