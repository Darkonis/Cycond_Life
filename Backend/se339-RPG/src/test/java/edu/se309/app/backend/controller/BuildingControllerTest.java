package edu.se309.app.backend.controller;

import edu.se309.app.backend.entity.Building;
import edu.se309.app.backend.service.interfaces.BuildingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuildingControllerTest {

    @InjectMocks
    private BuildingController buildingController;

    @Mock
    private BuildingService buildingService;

    private Building building;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        building = mock(Building.class);
         buildingService = buildingController.getService();
    }

    @Test
    void findEarnedStatByLocation() {
        String earnedStat = building.getEarnedStat();
        when(buildingService.findEarnedStatFromLocation(anyString(), anyString())).thenReturn(earnedStat);
        String newBuilding = buildingController.findEarnedStatByLocation("Longitude", "Latitude");
        assertEquals(newBuilding, building.getEarnedStat());
    }

    @Test
    void findBuildingNameLocation() {
        String buildingName = building.getBuildingName();
        when(buildingService.findBuildingNameFromLocation(anyString(), anyString())).thenReturn(buildingName);
        String newBuilding = buildingController.findBuildingNameLocation("Longitude", "Latitude");
        assertEquals(newBuilding, building.getBuildingName());
    }

    @Test
    public void count() {
        long expected = 1L;
        when(buildingService.count()).thenReturn(expected);
        long count = buildingController.count();
        assertEquals(expected, count);
    }

    @Test
    public void deleteById() {
        //TODO
    }

    @Test
    public void findAll() {
        List<Building> buildings = new ArrayList<>();
        buildings.add(building);
        Building building2 = mock(Building.class);
        buildings.add(building2);
        when(buildingService.findAll()).thenReturn(buildings);
        List<Building> newBuildings = buildingController.findAll();
        for (Building b : buildings) {
            assertTrue(newBuildings.contains(b));
        }
    }

    @Test
    public void findById() {
        when(buildingService.findById(building.getId())).thenReturn(building);
        Building newBuilding = buildingController.findById(building.getId());
        assertEquals(newBuilding.getId(), building.getId());
    }
}