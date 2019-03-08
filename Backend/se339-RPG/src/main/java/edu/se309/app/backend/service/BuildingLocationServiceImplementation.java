package edu.se309.app.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.transaction.annotation.Transactional;

import edu.se309.app.backend.repository.interfaces.BuildingLocationRepository;
import edu.se309.app.backend.service.interfaces.BuildingLocationService;

public class BuildingLocationServiceImplementation implements BuildingLocationService {

	private BuildingLocationRepository buildingLocationRepository;

	@Autowired
	public BuildingLocationServiceImplementation(BuildingLocationRepository buildingLocationRepository) {
		this.buildingLocationRepository = buildingLocationRepository;
	}

	@Override
	@Transactional
	public String findBuildingStat(Point point) {
		return buildingLocationRepository.findBuildingStat(point).getEarnedStat().toLowerCase();
	}

}
