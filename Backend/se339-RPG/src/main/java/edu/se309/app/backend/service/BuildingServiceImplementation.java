package edu.se309.app.backend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.se309.app.backend.entity.Building;
import edu.se309.app.backend.repository.BuildingRepository;
import edu.se309.app.backend.repository.BuildingRepositoryCustom;
import edu.se309.app.backend.service.interfaces.BuildingService;

@Service
public class BuildingServiceImplementation extends BaseServiceImplementation<Building, Integer, BuildingRepository>
  implements BuildingService {

	BuildingRepositoryCustom buildingRepositoryCustom;
  @Autowired
  public BuildingServiceImplementation(BuildingRepository buildingRepository, BuildingRepositoryCustom buildingRepositoryCustom) {
    super(buildingRepository);
    this.buildingRepositoryCustom = buildingRepositoryCustom;

  }

@Override
@Transactional
public String findEarnedStatFromLocation(String longitude, String latitude) {	
	return buildingRepositoryCustom.findBuildingStat(longitude,latitude);
	
}
	@Override
	@Transactional
	public String findBuildingNameFromLocation(String longitude, String latitude) {	
		return buildingRepositoryCustom.findBuildingName(longitude,latitude);	
}


  
}
