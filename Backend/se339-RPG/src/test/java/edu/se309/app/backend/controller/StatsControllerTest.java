package edu.se309.app.backend.controller;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.entity.UserStat;
import edu.se309.app.backend.service.interfaces.AccountService;
import edu.se309.app.backend.service.interfaces.BuildingService;
import edu.se309.app.backend.service.interfaces.StatService;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatsControllerTest {

    @InjectMocks
    private StatsController statsController;

    @Mock
    private StatService statService;

    @Mock
    private BuildingService buildingService;

    @Mock
    private AccountService accountService;

    private UserStat stat;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        stat = mock(UserStat.class);
        buildingService = statsController.getBuildingService();
        accountService = statsController.getAccountService();
        statService = statsController.getService();
    }

    @Test
    void count() {
        long expected = 1L;
        when(statService.count()).thenReturn(expected);
        long count = statsController.count();
        assertEquals(expected, count);
    }

    @Test
    void deleteById() {
        //TODO
    }

    @Test
    void findAll() {
        UserStat stat2 = mock(UserStat.class);
        List<UserStat> stats = new ArrayList<>();
        stats.add(stat);
        stats.add(stat2);
        when(statService.findAll()).thenReturn(stats);
        List<UserStat> newStats = statsController.findAll();
        for (UserStat a : stats) {
            assertTrue(newStats.contains(a));
        }
    }

    @Test
    void findById() {
        when(statService.findById(stat.getId())).thenReturn(stat);
        UserStat newStats = statsController.findById(stat.getId());
        assertEquals(stat.getId(), newStats.getId());
    }

    @Test
    void incrementStat() {
        when(statService.incrementByOne(anyInt(),anyString())).thenReturn(stat);
        UserStat newStat = statsController.incrementStat(stat.getId(), "Critical_Thinking");
        assertEquals(stat.getId(), newStat.getId());
    }

    @Test
    void modifyUserStats() {
        UserStat changeTo = mock(UserStat.class);
        changeTo.setId(stat.getId());
        when(statService.findById(stat.getId())).thenReturn(stat);
        UserStat newStat = statsController.modifyUserStats(changeTo.getId(), changeTo);
        assertEquals(changeTo.getId(), newStat.getId());
    }

    @Test
    void updateStat() {
        when(statService.updateUserStat(anyInt(), anyString(), anyInt())).thenReturn(stat);
        UserStat newStat = statsController.updateStat(stat.getId(), "Critical_Thinking", 100);
        assertEquals(stat, newStat);

    }

    @Test
    void getStatsByUsername() {
        when(statService.getByUsername(anyString())).thenReturn(stat);
        UserStat newStats = statsController.getStatsByUsername("SomeUserName");
        assertEquals(stat.getId(), newStats.getId());
    }

    @Test
    void createStats() {
        Account account = mock(Account.class);
        when(accountService.findById(stat.getId())).thenReturn(account);
        UserStat newStat = statsController.createStats(stat);
        assertEquals(newStat.getAccount(), account);
    }

    @Test
    void updateStatByLocation() {
        when(buildingService.findEarnedStatFromLocation(anyString(), anyString())).thenReturn("none");
        String outcome = statsController.UpdateStatByLocation(stat.getId(), "SomeLongitude", "SomeLatitude");
        assertEquals("No stats were updated based on location", outcome);
        when(buildingService.findEarnedStatFromLocation(anyString(), anyString())).thenReturn("Critical_Thinking");
        when(statService.incrementByOne(anyInt(), anyString())).thenReturn(stat);
        String expected = "Critical_Thinking" + " was updated by one. \n current stats: \n" + stat.toString();
        outcome = statsController.UpdateStatByLocation(stat.getId(), "SomeLongitude", "SomeLatitude");
        assertEquals(expected, outcome);

    }
}