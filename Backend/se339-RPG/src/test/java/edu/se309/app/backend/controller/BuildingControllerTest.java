package edu.se309.app.backend.controller;

import edu.se309.app.backend.entity.Building;
import edu.se309.app.backend.service.interfaces.BuildingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class BuildingControllerTest implements BaseControllerTest {

    @InjectMocks
    private BuildingController buildingController;

    @Mock
    private BuildingService buildingService;

    private Building building;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        building = new Building();
        building.setBuildingName("some name");
        building.setEarnedStat("none");
        building.setId(1);
    }

    @Test
    void findEarnedStatByLocation() {
        when(buildingService.findEarnedStatFromLocation(anyString(), anyString())).thenReturn(building.getEarnedStat());
        String newBuilding = buildingController.findEarnedStatByLocation(anyString(), anyString());
        assertEquals(newBuilding, building.getEarnedStat());

    }

    @Test
    void findBuildingNameLocation() {
        when(buildingService.findBuildingNameFromLocation(anyString(), anyString())).thenReturn(building.getBuildingName());
        String newBuilding = buildingController.findBuildingNameLocation(anyString(), anyString());
        assertEquals(newBuilding, building.getBuildingName());
    }

    @Override
    @Test
    public void count() {
        long expected = 1L;
        when(buildingService.count()).thenReturn(expected);
        long count = buildingController.count();
        assertEquals(expected, count);

    }

    @Override
    @Test
    public void deleteById() {
        //TODO
    }

    @Override
    @Test
    public void findAll() {
        List<Building> buildings = new ArrayList<>();
        buildings.add(building);
        Building building2 = new Building();
        building2.setBuildingName("some other name");
        building2.setEarnedStat("none");
        building2.setId(2);
        buildings.add(building2);
        when(buildingService.findAll()).thenReturn(buildings);
        List<Building> newBuilding = buildingController.findAll();
        for (Building b : buildings) {
            assertTrue(newBuilding.contains(b));
        }

    }

    @Override
    @Test
    public void findById() {
        when(buildingService.findById(building.getId())).thenReturn(building);
        Building newBuilding = buildingController.findById(building.getId());
        assertEquals(newBuilding.getId(), building.getId());

    }
}