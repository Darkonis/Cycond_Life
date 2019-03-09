package edu.se309.app.backend.repository;

import org.springframework.data.geo.Point;

import edu.se309.app.backend.entity.Building;

public interface BuildingRepository extends BaseRepository<Building,Integer>{
  Building findBuildingStat(Point point);
}
