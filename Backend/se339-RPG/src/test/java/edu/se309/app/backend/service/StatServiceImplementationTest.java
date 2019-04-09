package edu.se309.app.backend.service;

import edu.se309.app.backend.rest.entity.Account;
import edu.se309.app.backend.rest.entity.UserStat;
import edu.se309.app.backend.rest.service.StatServiceImplementation;
import edu.se309.app.backend.rest.repository.UserStatRepository;
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
class StatServiceImplementationTest {

    @InjectMocks
    private StatServiceImplementation statService;

    @Mock
    private UserStatRepository statRepository;

    private UserStat stat;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        stat = mock(UserStat.class);
        statRepository = statService.getRepository();
    }

    @Test
    void count() {
        long expected = 1L;
        when(statRepository.count()).thenReturn(expected);
        long actual = statService.count();
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        doNothing().when(statRepository).deleteById(anyInt());
        statService.deleteById(stat.getId());
    }

    @Test
    void findAll() {
        List<UserStat> stats = new ArrayList<>();
        stats.add(stat);
        stats.add(mock(UserStat.class));
        when(statRepository.findAll()).thenReturn(stats);
        List<UserStat> newStats = statService.findAll();
        for (UserStat a : stats) {
            assertTrue(newStats.contains(a));
        }
    }

    @Test
    void findById() {
        int id = stat.getId();
        when(statRepository.findById(id)).thenReturn(Optional.of(stat));
        UserStat newStat = statService.findById(id);
        assertEquals(stat, newStat);
    }

    @Test
    void nullCheck() {
        assertThrows(ServiceException.class,
                () -> statService.nullCheck(Optional.empty(), "Empty Test"));
        assertEquals(stat, statService.nullCheck(Optional.of(stat), "Stat Test"));
    }

    @Test
    void save() {
        when(statRepository.save(stat)).thenReturn(stat);
        statService.save(stat);
    }

    @Test
    void incrementByAmount() {
        int amount = 5;
        String fakeStat = "NotStat";
        when(statRepository.findById(stat.getId())).thenReturn(Optional.of(stat));
        when(statRepository.save(stat)).thenReturn(stat);
        UserStat actual = statService.incrementByAmount(stat.getId(), "criticalThinking", amount);
        Boolean failed = false;
        assertEquals(stat, actual);
        try {
            statService.incrementByAmount(stat.getId(),fakeStat,amount);
        } catch (Exception e) {
        	failed = true;
        }
        assertTrue(failed);
    }

    @Test
    void incrementByOne() {
        String fakeStat = "NotStat";
        when(statRepository.findById(stat.getId())).thenReturn(Optional.of(stat));
        when(statRepository.save(stat)).thenReturn(stat);
        UserStat actual = statService.incrementByOne(stat.getId(), "criticalThinking");
        Boolean failed = false;
        assertEquals(stat, actual);
        try {
            statService.incrementByOne(stat.getId(),fakeStat);
        } catch (Exception e) {
        	failed = true;
        }
        assertTrue(failed);
    }

    @Test
    void updateUserStat() {
        int amount = 5;
        String fakeStat = "NotStat";
        when(statRepository.findById(stat.getId())).thenReturn(Optional.of(stat));
        when(statRepository.save(stat)).thenReturn(stat);
        UserStat actual = statService.updateUserStat(stat.getId(), "criticalThinking", amount);
        assertEquals(stat, actual);
        try {
            statService.updateUserStat(stat.getId(),fakeStat,amount);
        } catch (ServiceException e) {
            String expected = "Invalid request: No such stat found: " + fakeStat;
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    void getByUsername() {
        String username = "Username";
        when(statRepository.findByAccountUsername(username))
                .thenReturn(Optional.of(stat));
        UserStat actual = statService.getByUsername("Username");
        assertEquals(stat,actual);

    }


}