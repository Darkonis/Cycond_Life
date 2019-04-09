package edu.se309.app.backend.rest.service.interfaces;


import edu.se309.app.backend.rest.entity.Building;

public interface BuildingService extends BaseService<Building, Integer> {

    String findBuildingNameFromLocation(String longitude, String latitude);

    String findEarnedStatFromLocation(String longitude, String latitude);
}
