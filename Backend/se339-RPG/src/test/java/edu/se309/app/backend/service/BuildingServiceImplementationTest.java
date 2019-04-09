package edu.se309.app.backend.service;

import edu.se309.app.backend.rest.entity.Building;
import edu.se309.app.backend.rest.repository.BuildingRepository;
import edu.se309.app.backend.rest.repository.BuildingRepositoryCustom;
import edu.se309.app.backend.rest.service.BuildingServiceImplementation;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuildingServiceImplementationTest {

    @InjectMocks
    private BuildingServiceImplementation buildingService;

    @Mock
    private BuildingRepositoryCustom buildingRepositoryCustom;

    @Mock
    private BuildingRepository buildingRepository;

    private Building building;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        building = mock(Building.class);
        buildingRepository = buildingService.getRepository();
        buildingRepositoryCustom = buildingService.getCustomRepository();
    }

    @Test
    void count() {
        long expected = 1L;
        when(buildingRepository.count()).thenReturn(expected);
        long actual = buildingService.count();
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        doNothing().when(buildingRepository).deleteById(anyInt());
        buildingService.deleteById(building.getId());
    }

    @Test
    void findAll() {
        List<Building> buildings = new ArrayList<>();
        buildings.add(building);
        buildings.add(mock(Building.class));
        when(buildingRepository.findAll()).thenReturn(buildings);
        List<Building> newBuildings = buildingService.findAll();
        for (Building a : buildings) {
            assertTrue(newBuildings.contains(a));
        }
    }

    @Test
    void findById() {
        int id = building.getId();
        when(buildingRepository.findById(id)).thenReturn(Optional.of(building));
        Building newBuilding = buildingService.findById(id);
        assertEquals(building, newBuilding);
    }

    @Test
    void nullCheck() {
        assertThrows(ServiceException.class,
                () -> buildingService.nullCheck(Optional.empty(), "Empty Test"));
        assertEquals(building, buildingService.nullCheck(Optional.of(building), "Building Test"));
    }

    @Test
    void save() {
        when(buildingRepository.save(building)).thenReturn(building);
        buildingService.save(building);
    }

    @Test
    void findEarnedStatFromLocation() {
        String stat = "none";
        when(buildingRepositoryCustom.findBuildingStat(anyString(), anyString())).thenReturn(stat);
        String actual = buildingService.findEarnedStatFromLocation("longitude", "latitude");
        assertEquals(stat, actual);
    }

    @Test
    void findBuildingNameFromLocation() {
        String buildingName = "none";
        when(buildingRepositoryCustom.findBuildingName(anyString(), anyString())).thenReturn(buildingName);
        String actual = buildingService.findBuildingNameFromLocation("longitude", "latitude");
        assertEquals(buildingName, actual);
    }
}