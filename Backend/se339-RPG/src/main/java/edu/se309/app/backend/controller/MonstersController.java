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
import edu.se309.app.backend.repository.Interfaces.MonstersRepository;


@RestController
@RequestMapping("/monster")
public class MonstersController 
{
	@Autowired
	MonstersRepository monstersRepository;
	
	private final Logger logger = LoggerFactory.getLogger(MonstersController.class);
	
	/**
	 * Lists the current monsters in a html form for testing.
	 * @return
	 * 		The html for the list of all monsters.
	 */
	@GetMapping("/list")
	public List<Monster> findAll()
	{
		logger.info("Entered into Controller Layer");
		List<Monster>result = monstersRepository.findAll();
		logger.info("Number of Records Fetched:" + result.size());
		return result;
	}
	/**
	 * View a specific monster's data
	 * @param id 
	 * 			A monster's id
	 * @return
	 * 			The monster's data
	 */
	@GetMapping("/list/{monsterId}")
	public Monster findById(@PathVariable("monsterId") int id)
	{
		 logger.info("Entered into Controller Layer");
		 Monster results = monstersRepository.getOne(id); 
		return results;
	}
	
	/**
	 * Deletes the current table, and replaces it with 50 new monsters.
	 * @return
	 * 		The html for the page to return to the sub-menu and list
	 */
	@GetMapping("/generate")
	public String generateMonster()
	{
		monstersRepository.deleteAll();//deletes the current list
        int num_monsters = 50;
        int j = 0;
        Random rand = new Random(0);
            //add Campinile base
        	for(int i = 0; i < num_monsters;i++)
        	{
        		double lat = (rand.nextInt()%3)/1000.0 + 42.0254;
        		double lon = (rand.nextInt()%3)/1000.0 - 93.6461;
        		Monster newMon = new Monster();
                newMon.setType(Math.abs(1));//sets the type for the monster
                newMon.setLat(lat);//sets the latitude for the monster
                newMon.setLon(lon);//sets the longitude for the monster
                newMon.setId(j);//sets the id for the monster
                j++;
                monstersRepository.save(newMon);//saves the monster to the sql list
        	}
        	//add Hoover base
        	for(int i = 0; i < num_monsters;i++)
        	{
        		double lat = (rand.nextInt()%3)/1000.0 + 42.0267;
        		double lon = (rand.nextInt()%3)/1000.0 - 93.6512;
        		Monster newMon = new Monster();
                newMon.setType(2);//sets the type for the monster
                newMon.setLat(lat);//sets the latitude for the monster
                newMon.setLon(lon);//sets the longitude for the monster
                newMon.setId(j);//sets the id for the monster
                j++;
                monstersRepository.save(newMon);//saves the monster to the sql list
        	}
        	//add Physics base
        	for(int i = 0; i < num_monsters;i++)
        	{
        		double lat = (rand.nextInt()%3)/1000.0 + 42.0295;
        		double lon = (rand.nextInt()%3)/1000.0 - 93.6473;
        		Monster newMon = new Monster();
                newMon.setType(3);//sets the type for the monster
                newMon.setLat(lat);//sets the latitude for the monster
                newMon.setLon(lon);//sets the longitude for the monster
                newMon.setId(j);//sets the id for the monster
                j++;
                monstersRepository.save(newMon);//saves the monster to the sql list
        	}
        	//add troxell base
        	for(int i = 0; i < num_monsters;i++)
        	{
        		double lat = (rand.nextInt()%6)/1000.0 + 42.0308;
        		double lon = (rand.nextInt()%6)/1000.0 - 93.6536;
        		Monster newMon = new Monster();
                newMon.setType(4);//sets the type for the monster
                newMon.setLat(lat);//sets the latitude for the monster
                newMon.setLon(lon);//sets the longitude for the monster
                newMon.setId(j);//sets the id for the monster
                j++;
                monstersRepository.save(newMon);//saves the monster to the sql list
        	}
        	//isu cemetary base
        	for(int i = 0; i < num_monsters;i++)
        	{
        		double lat = (rand.nextInt()%3)/1000.0 + 42.0278;
        		double lon = (rand.nextInt()%3)/1000.0 - 93.6440;
        		Monster newMon = new Monster();
                newMon.setType(5);//sets the type for the monster
                newMon.setLat(lat);//sets the latitude for the monster
                newMon.setLon(lon);//sets the longitude for the monster
                newMon.setId(j);//sets the id for the monster
                j++;
                monstersRepository.save(newMon);//saves the monster to the sql list
        	}
	return "Finished<br><br><a href=\"http://localhost:8080/monster\">return</a><br><a href=\"http://localhost:8080/monster/list\">List of Current Monsters</a>";
	}
}
