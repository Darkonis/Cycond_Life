package edu.se309.app.backend.rest.controller;

import edu.se309.app.backend.rest.entity.Building;
import edu.se309.app.backend.rest.service.interfaces.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Building Controller
 */
@RestController
@RequestMapping("/api/building")
public class BuildingController extends BaseController<Building, Integer, BuildingService> {

    /**
     * Constructor for this Controller
     *
     * @param buildingService Service associated with this controller
     */
    @Autowired
    public BuildingController(BuildingService buildingService) {
        super(buildingService);

    }

    /**
     * Find the stat associated with the given location
     * @param longitude longitude of location
     * @param latitude latitude of location
     * @return the stat associated with the given location
     */
    @GetMapping("/getStat/{longitude}/{latitude}/")
    public String findEarnedStatByLocation(@PathVariable String longitude, @PathVariable String latitude) {

        return getService().findEarnedStatFromLocation(longitude, latitude);
    }

    /**
     * Find the name associated with the given location
     * @param longitude longitude of location
     * @param latitude latitude of location
     * @return the name associated with the given location
     */
    @GetMapping("/getBuildingName/{longitude}/{latitude}/")
    public String findBuildingNameLocation(@PathVariable String longitude, @PathVariable String latitude) {

        return getService().findBuildingNameFromLocation(longitude, latitude);
    }


}
