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
	
	@RequestMapping(method = RequestMethod.GET, path = "/monster/list")
	public List<monsters> findAll()
	{
		logger.info("Entered into Controller Layer");
		List<monsters>result = monstersRepository.findAll();
		logger.info("Number of Records Fetched:" + result.size());
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, path ="/monster/generate")
	public String generateMonster()
	{
        boolean valid=false;
        int num_monsters = 50;
        Random rand = new Random(0);
            //add ames base

            for (int i=0;i<num_monsters;i++)
            {
                double lat = (rand.nextInt() % 100) / 1000.0 + 42.03 - .05;
                double lon = (rand.nextInt() % 100) / 1000.0 + 93.63 - .05;
                monsters newMon = new monsters();
                newMon.setType("mon");
                newMon.setLat(lat);
                newMon.setLon(lon);
                monstersRepository.save(newMon);
            }
	return "Finished";
	}
}
