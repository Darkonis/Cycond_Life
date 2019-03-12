package edu.se309.app.backend.controller;

import edu.se309.app.backend.entity.Building;
import edu.se309.app.backend.service.interfaces.BuildingService;

public class BuildingController extends BaseController<Building, Integer, BuildingService> 
{

  public BuildingController(BuildingService buildingService) {
    super(buildingService);
    
  }
  
}
