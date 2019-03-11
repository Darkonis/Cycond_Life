package edu.se309.app.backend.service;

import org.springframework.beans.factory.annotation.Autowired;

import edu.se309.app.backend.entity.Building;
import edu.se309.app.backend.repository.BuildingRepository;
import edu.se309.app.backend.service.interfaces.BuildingService;

public class BuildingServiceImplementation extends BaseServiceImplementation<Building, Integer, BuildingRepository>
  implements BuildingService {

  @Autowired
  public BuildingServiceImplementation(BuildingRepository buildingRepository) {
    super(buildingRepository);

  }
  
}
