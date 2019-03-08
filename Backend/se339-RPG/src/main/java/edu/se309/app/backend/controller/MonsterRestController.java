package edu.se309.app.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.se309.app.backend.entity.Monster;
import edu.se309.app.backend.service.interfaces.MonsterService;
import io.swagger.annotations.ApiOperation;


@RestController

@RequestMapping("/api/monsters")
public class MonsterRestController 
{
	private MonsterService monsterService;
	
	@Autowired
	public MonsterRestController(MonsterService monsterService) {
		this.monsterService = monsterService;
	}
	
	/**
	 * Lists the current monsters in a html form for testing.
	 * @return
	 * 		The html for the list of all monsters.
	 */
	@GetMapping("/")
	public List<Monster> findAll()
	{		
		return monsterService.findAll();
	}
	/**
	 * View a specific monster's data
	 * @param id 
	 * 			A monster's id
	 * @return
	 * 			The monster's data
	 */
	@GetMapping("/{monsterID}")
	public Monster findById(@PathVariable("monsterID") int id)
	{
		return monsterService.findById(id);
	}
	
	/**
	 * Deletes the current table, and replaces it with 50 new monsters.
	 * @return
	 * 		The html for the page to return to the sub-menu and list
	 */
	@PostMapping("/generate")
	public List<Monster> generateMonster()
	{
		return monsterService.generateMonsters();		
	}
	
	@ApiOperation(value="returns first moster's id or -1 if the list is empty")
	@GetMapping("/fistId")
	public int getFirstId(){
		return monsterService.firstMonsterId();
	}
}
