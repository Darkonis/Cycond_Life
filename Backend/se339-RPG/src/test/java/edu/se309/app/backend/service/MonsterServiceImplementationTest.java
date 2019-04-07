package edu.se309.app.backend.service;

import edu.se309.app.backend.entity.Monster;
import edu.se309.app.backend.repository.MonsterRepository;
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
class MonsterServiceImplementationTest {

    @InjectMocks
    private MonsterServiceImplementation monsterService;

    @Mock
    private MonsterRepository monsterRepository;

    private Monster monster;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        monster = mock(Monster.class);
        monsterRepository = monsterService.getRepository();
    }

    @Test
    void count() {
        long expected = 1L;
        when(monsterRepository.count()).thenReturn(expected);
        long actual = monsterService.count();
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        doNothing().when(monsterRepository).deleteById(anyInt());
        monsterService.deleteById(monster.getId());
    }

    @Test
    void findAll() {
        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster);
        monsters.add(mock(Monster.class));
        when(monsterRepository.findAll()).thenReturn(monsters);
        List<Monster> newMonsters = monsterService.findAll();
        for (Monster a : monsters) {
            assertTrue(newMonsters.contains(a));
        }
    }

    @Test
    void findById() {
        int id = monster.getId();
        when(monsterRepository.findById(id)).thenReturn(Optional.of(monster));
        Monster newMonster = monsterService.findById(id);
        assertEquals(monster, newMonster);
    }

    @Test
    void nullCheck() {
        assertThrows(ServiceException.class,
                () -> monsterService.nullCheck(Optional.empty(), "Empty Test"));
        assertEquals(monster, monsterService.nullCheck(Optional.of(monster), "Monster Test"));
    }

    @Test
    void save() {
        when(monsterRepository.save(monster)).thenReturn(monster);
        monsterService.save(monster);
    }

    @Test
    void deleteAll() {
        doNothing().when(monsterRepository).deleteAll();
        monsterService.deleteAll();
    }

    @Test
    void firstMonsterId() {
        when(monsterRepository.findFirstByOrderByIdAsc()).thenReturn(Optional.of(monster));
        assertEquals(monster.getId(), monsterService.firstMonsterId());
        when(monsterRepository.findFirstByOrderByIdAsc()).thenReturn(Optional.empty());
        assertEquals(-1, monsterService.firstMonsterId());
    }

    @Test
    void generateMonsters() {
        //TODO
    }
}