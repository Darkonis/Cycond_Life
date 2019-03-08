package edu.se309.app.backend.repository.interfaces;

import org.springframework.data.geo.Point;
import edu.se309.app.backend.entity.BuildingLocation;

public interface BuildingLocationRepository { BuildingLocation findBuildingStat(Point point); }
