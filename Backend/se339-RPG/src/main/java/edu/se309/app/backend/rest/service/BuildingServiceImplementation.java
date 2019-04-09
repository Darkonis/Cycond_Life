package edu.se309.app.backend.rest.service;

import edu.se309.app.backend.rest.entity.Building;
import edu.se309.app.backend.rest.repository.BuildingRepository;
import edu.se309.app.backend.rest.repository.BuildingRepositoryCustom;
import edu.se309.app.backend.rest.service.interfaces.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        return buildingRepositoryCustom.findBuildingStat(longitude, latitude);

    }

    @Override
    @Transactional
    public String findBuildingNameFromLocation(String longitude, String latitude) {
        return buildingRepositoryCustom.findBuildingName(longitude, latitude);
    }

    public BuildingRepositoryCustom getCustomRepository() {
        return buildingRepositoryCustom;
    }


}
