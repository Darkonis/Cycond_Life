package edu.se309.app.backend.service.interfaces;

import org.springframework.data.geo.Point;

public interface BuildingLocationService { String findBuildingStat(Point point); }
