package edu.se309.app.backend.service;

import org.springframework.data.geo.Point;

public interface BuildingLocationService {
	
	public String findBuildingStat(Point point);
}
