package edu.se309.app.backend.repository.Interfaces;

import org.springframework.data.geo.Point;

import edu.se309.app.backend.entity.BuildingLocation;

public interface BuildingLocationDAO {	
	
	public BuildingLocation findBuildingStat(Point point);
}
