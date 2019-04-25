package edu.se309.app.backend.rest.service;

import edu.se309.app.backend.rest.entity.Building;
import edu.se309.app.backend.rest.repository.BuildingRepository;
import edu.se309.app.backend.rest.repository.BuildingRepositoryCustom;
import edu.se309.app.backend.rest.service.interfaces.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Building Services
 */
@Service
public class BuildingServiceImplementation extends BaseServiceImplementation<Building, Integer, BuildingRepository>
        implements BuildingService {

    BuildingRepositoryCustom buildingRepositoryCustom;

    /**
     * Constructor for Building Services
     *
     * @param buildingRepository       Building Repository associated with this service
     * @param buildingRepositoryCustom Custom Repository associated with this services
     */
    @Autowired
    public BuildingServiceImplementation(BuildingRepository buildingRepository, BuildingRepositoryCustom buildingRepositoryCustom) {
        super(buildingRepository);
        this.buildingRepositoryCustom = buildingRepositoryCustom;
    }

    /**
     * Find the stat associated with location
     *
     * @param longitude longitude
     * @param latitude  latitude
     * @return
     */
    @Override
    @Transactional
    public String findEarnedStatFromLocation(String longitude, String latitude) {
        return buildingRepositoryCustom.findBuildingStat(longitude, latitude);

    }

    /**
     * Find the name of the building associated with a location
     *
     * @param longitude longitude
     * @param latitude  latitude
     * @return building's name
     */
    @Override
    @Transactional
    public String findBuildingNameFromLocation(String longitude, String latitude) {
        return buildingRepositoryCustom.findBuildingName(longitude, latitude);
    }

    /**
     * returns custom repository associated with this service
     *
     * @return custom repository
     */
    public BuildingRepositoryCustom getCustomRepository() {
        return buildingRepositoryCustom;
    }


}
