package edu.se309.app.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.transaction.annotation.Transactional;

import edu.se309.app.backend.repository.BuildingLocationDAO;

public class BuildingLocationServiceImplementation implements BuildingLocationService {	

	
private BuildingLocationDAO buildingLocationDAO;
	
	@Autowired
	public BuildingLocationServiceImplementation(BuildingLocationDAO buildingLocationDAO) {
		this.buildingLocationDAO = buildingLocationDAO;
	}
	
	@Override
	@Transactional
	public String findBuildingStat(Point point) {
		return buildingLocationDAO.findBuildingStat(point).getEarnedStat().toLowerCase();		
	}

}
