package edu.se309.app.backend.rest.controller;

import edu.se309.app.backend.rest.entity.Building;
import edu.se309.app.backend.rest.service.interfaces.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/building")
public class BuildingController extends BaseController<Building, Integer, BuildingService> {

    @Autowired
    public BuildingController(BuildingService buildingService) {
        super(buildingService);

    }

    @GetMapping("/getStat/{longitude}/{latitude}/")
    public String findEarnedStatByLocation(@PathVariable String longitude, @PathVariable String latitude) {

        return getService().findEarnedStatFromLocation(longitude, latitude);
    }

    @GetMapping("/getBuildingName/{longitude}/{latitude}/")
    public String findBuildingNameLocation(@PathVariable String longitude, @PathVariable String latitude) {

        return getService().findBuildingNameFromLocation(longitude, latitude);
    }


}
