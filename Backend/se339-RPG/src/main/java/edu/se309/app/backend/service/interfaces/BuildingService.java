package edu.se309.app.backend.service.interfaces;



import edu.se309.app.backend.entity.Building;

public interface BuildingService extends BaseService<Building, Integer> {
	
	String findBuildingNameFromLocation(String longitude, String latitude);
	String findEarnedStatFromLocation(String longitude, String latitude);	
}
