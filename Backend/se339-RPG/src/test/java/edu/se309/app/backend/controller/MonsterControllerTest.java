package edu.se309.app.backend.controller;

import edu.se309.app.backend.rest.controller.MonsterController;
import edu.se309.app.backend.rest.entity.Monster;
import edu.se309.app.backend.rest.service.interfaces.MonsterService;
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
class MonsterControllerTest {

    @InjectMocks
    private MonsterController monsterController;

    @Mock
    private MonsterService monsterService;

    private Monster monster;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        monster = mock(Monster.class);
        monsterService = monsterController.getService();
    }


    @Test
    void count() {
        long expected = 1L;
        when(monsterService.count()).thenReturn(expected);
        long count = monsterController.count();
        assertEquals(expected, count);
    }

    @Test
    void deleteById() {
        String expected = "Deleted Monster with id: " + monster.getId();
        String actual = monsterController.deleteById(monster.getId());
        assertEquals(expected, actual);
    }

    @Test
    void findAll() {
        Monster monster2 = mock(Monster.class);
        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster);
        monsters.add(monster2);
        when(monsterService.findAll()).thenReturn(monsters);
        List<Monster> newMonsters = monsterController.findAll();
        for (Monster a : monsters) {
            assertTrue(newMonsters.contains(a));
        }
    }

    @Test
    void findById() {
        when(monsterService.findById(monster.getId())).thenReturn(monster);
        Monster newMonster = monsterController.findById(monster.getId());
        assertEquals(monster.getId(), newMonster.getId());
    }

    @Test
    void generateMonster() {
        Monster monster2 = mock(Monster.class);
        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster);
        monsters.add(monster2);
        when(monsterService.generateMonsters()).thenReturn(monsters);
        List<Monster> newMonsters = monsterController.generateMonster();
        for (Monster a : monsters) {
            assertTrue(newMonsters.contains(a));
        }
    }

    @Test
    void getFirstId() {
        int id = monster.getId();
        when(monsterService.firstMonsterId()).thenReturn(id);
        int newMonster = monsterController.getFirstId();
        assertEquals(monster.getId(), newMonster);
    }
}