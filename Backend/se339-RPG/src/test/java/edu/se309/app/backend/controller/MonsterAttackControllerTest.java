package edu.se309.app.backend.controller;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.entity.MonsterStat;
import edu.se309.app.backend.service.interfaces.MonsterStatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MonsterAttackControllerTest {

    @InjectMocks
    private MonsterStatController monsterStatController;

    @Mock
    private MonsterStatService monsterStatService;

    @Mock
    private MonsterStat monsterStat;

    @BeforeEach
    public void setup() {
        monsterStat = mock(MonsterStat.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void count() {
        long expected = 1L;
        when(monsterStatService.count()).thenReturn(expected);
        long count = monsterStatController.count();
        assertEquals(expected, count);
    }

    @Test
    public void deleteById() {
        //TODO
    }

    @Test
    public void findAll() {
        MonsterStat monsterStat2 = mock(MonsterStat.class);
        List<MonsterStat> monsterStats = new ArrayList<>();
        monsterStats.add(monsterStat);
        monsterStats.add(monsterStat2);
        when(monsterStatService.findAll()).thenReturn(monsterStats);
        List<MonsterStat> newMonsterStats = monsterStatController.findAll();
        for (MonsterStat m : monsterStats) {
            assertTrue(newMonsterStats.contains(m));
        }
    }

    @Test
    public void findById() {
        when(monsterStatService.findById(monsterStat.getId())).thenReturn(monsterStat);
        MonsterStat newMonsterStat = monsterStatController.findById(monsterStat.getId());
        assertEquals(monsterStat.getId(), newMonsterStat.getId());
    }
}