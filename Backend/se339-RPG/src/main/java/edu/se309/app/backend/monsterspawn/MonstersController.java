package edu.se309.app.backend.monsterspawn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import edu.se309.app.backend.monsterspawn.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ArrayList;


@RestController
public class MonstersController 
{
	@Autowired
	MonstersRepository monstersRepository;
	
	private final Logger logger = LoggerFactory.getLogger(MonstersController.class);
	
	/**
	 * The base page, used for navigation
	 * @return
	 * 		The html for the sub-menu
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/monster")
	public String welcome()
	{
		return "Welcome to the monster controller<br><br><a href=\"http://localhost:8080/monster/list\">List of Current Monsters</a>"
				+ "<br><a href=\"http://localhost:8080/monster/generate\">Generate new list</a>";
	}
	/**
	 * Lists the current monsters in a html form for testing.
	 * @return
	 * 		The html for the list of all monsters.
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/monster/list")
	public String findAll()
	{
		logger.info("Entered into Controller Layer");
		List<monsters>result = monstersRepository.findAll();
		logger.info("Number of Records Fetched:" + result.size());
		String s = "";
		for(int i = 0; i < result.size(); i++)
		{
			s += "Monster Id: " + result.get(i).getId() + "<br>Monster Type: " + result.get(i).getType() + 
					"<br>Monster Longitude: " + result.get(i).getLon() + "<br>Monster Latitude: " 
					+ result.get(i).getLat() + "<br><br>";
		}
		s += "<a href=\"http://localhost:8080/monster\">return</a>";
		return s;
	}
	
	/**
	 * Deletes the current table, and replaces it with 50 new monsters.
	 * @return
	 * 		The html for the page to return to the sub-menu and list
	 */
	@RequestMapping(method = RequestMethod.GET, path ="/monster/generate")
	public String generateMonster()
	{
		monstersRepository.deleteAll();//deletes the current list
        int num_monsters = 50;
        Random rand = new Random(0);
            //add ames base

            for (int i=0;i<num_monsters;i++)
            {
                double lat = (rand.nextInt() % 100) / 1000.0 + 42.03 - .05;
                double lon = (rand.nextInt() % 100) / 1000.0 + 93.63 - .05;
                monsters newMon = new monsters();
                newMon.setType("mon");//sets the type for the monster
                newMon.setLat(lat);//sets the latitude for the monster
                newMon.setLon(lon);//sets the longitude for the monster
                newMon.setId(50-i);//sets the id for the monster
                monstersRepository.save(newMon);//saves the monster to the sql list
            }
	return "Finished<br><br><a href=\"http://localhost:8080/monster\">return</a><br><a href=\"http://localhost:8080/monster/list\">List of Current Monsters</a>";
	}
}
