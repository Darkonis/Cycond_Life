package edu.se309.app.backend.controller;

import edu.se309.app.backend.rest.controller.MonsterAttackController;
import edu.se309.app.backend.rest.entity.MonsterAttack;
import edu.se309.app.backend.rest.service.interfaces.MonsterAttackService;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MonsterAttackControllerTest {

    @InjectMocks
    private MonsterAttackController monsterAttackController;

    @Mock
    private MonsterAttackService monsterAttackService;

    @Mock
    private MonsterAttack monsterAttack;

    @BeforeEach
    public void setup() {
        monsterAttack = mock(MonsterAttack.class);
        MockitoAnnotations.initMocks(this);
        monsterAttackService = monsterAttackController.getService();
    }

    @Test
    public void count() {
        long expected = 1L;
        when(monsterAttackService.count()).thenReturn(expected);
        long count = monsterAttackController.count();
        assertEquals(expected, count);
    }

    @Test
    public void deleteById() {
        String expected = "Deleted MonsterAttack with id: " + monsterAttack.getId();
        String actual = monsterAttackController.deleteById(monsterAttack.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void findAll() {
        MonsterAttack monsterAttack2 = mock(MonsterAttack.class);
        List<MonsterAttack> monsterAttacks = new ArrayList<>();
        monsterAttacks.add(monsterAttack);
        monsterAttacks.add(monsterAttack2);
        when(monsterAttackService.findAll()).thenReturn(monsterAttacks);
        List<MonsterAttack> newMonsterAttacks = monsterAttackController.findAll();
        for (MonsterAttack m : monsterAttacks) {
            assertTrue(newMonsterAttacks.contains(m));
        }
    }

    @Test
    public void findById() {
        when(monsterAttackService.findById(monsterAttack.getId())).thenReturn(monsterAttack);
        MonsterAttack newMonsterAttack = monsterAttackController.findById(monsterAttack.getId());
        assertEquals(monsterAttack.getId(), newMonsterAttack.getId());
    }
}