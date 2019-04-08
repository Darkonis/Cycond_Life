package edu.se309.app.backend.service;

import edu.se309.app.backend.rest.entity.MonsterStat;
import edu.se309.app.backend.rest.repository.MonsterStatRepository;
import edu.se309.app.backend.rest.service.MonsterStatServiceImplementation;
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
class MonsterStatServiceImplementationTest {

    @InjectMocks
    private MonsterStatServiceImplementation monsterStatService;

    @Mock
    private MonsterStatRepository monsterStatRepository;

    private MonsterStat monsterStat;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        monsterStat = mock(MonsterStat.class);
        monsterStatRepository = monsterStatService.getRepository();
    }

    @Test
    void count() {
        long expected = 1L;
        when(monsterStatRepository.count()).thenReturn(expected);
        long actual = monsterStatService.count();
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        doNothing().when(monsterStatRepository).deleteById(anyInt());
        monsterStatService.deleteById(monsterStat.getId());
    }

    @Test
    void findAll() {
        List<MonsterStat> monsterStats = new ArrayList<>();
        monsterStats.add(monsterStat);
        monsterStats.add(mock(MonsterStat.class));
        when(monsterStatRepository.findAll()).thenReturn(monsterStats);
        List<MonsterStat> newMonsterStats = monsterStatService.findAll();
        for (MonsterStat a : monsterStats) {
            assertTrue(newMonsterStats.contains(a));
        }
    }

    @Test
    void findById() {
        int id = monsterStat.getId();
        when(monsterStatRepository.findById(id)).thenReturn(Optional.of(monsterStat));
        MonsterStat newMonsterStat = monsterStatService.findById(id);
        assertEquals(monsterStat,newMonsterStat);

    }

    @Test
    void nullCheck() {
        assertThrows(ServiceException.class,
                ()-> monsterStatService.nullCheck(Optional.empty(),"Empty Test"));
        assertEquals(monsterStat,monsterStatService.nullCheck(Optional.of(monsterStat),"MonsterStat Test"));
    }

    @Test
    void save() {
        when(monsterStatRepository.save(monsterStat)).thenReturn(monsterStat);
        monsterStatService.save(monsterStat);
    }
}