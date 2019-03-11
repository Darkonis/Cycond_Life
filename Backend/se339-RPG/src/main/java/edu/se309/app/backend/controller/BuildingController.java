package edu.se309.app.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.se309.app.backend.entity.Building;
import edu.se309.app.backend.service.interfaces.BuildingService;

@RestController
@RequestMapping("/api/building")
public class BuildingController extends BaseController<Building, Integer, BuildingService> {

	@Autowired
	public BuildingController(BuildingService buildingService) {
		super(buildingService);

	}
	
//	@Override
//	public List<Building> findAll() {
//		List<Building> broken= new ArrayList<>();
//		Building b = new Building(null,"Currently Broken");
//		broken.add(b);
//		return broken;
//	}

}
