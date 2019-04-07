package edu.se309.app.backend.service;

import edu.se309.app.backend.entity.MonsterAttack;
import edu.se309.app.backend.repository.MonsterAttackRepository;
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
class MonsterAttackServiceImplementationTest {

    @InjectMocks
    private MonsterAttackServiceImplementation monsterAttackService;

    @Mock
    private MonsterAttackRepository monsterAttackRepository;

    private MonsterAttack monsterAttack;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        monsterAttack = mock(MonsterAttack.class);
        monsterAttackRepository = monsterAttackService.getRepository();
    }

    @Test
    void count() {
        long expected = 1L;
        when(monsterAttackRepository.count()).thenReturn(expected);
        long actual = monsterAttackService.count();
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        doNothing().when(monsterAttackRepository).deleteById(anyInt());
        monsterAttackService.deleteById(monsterAttack.getId());
    }

    @Test
    void findAll() {
        List<MonsterAttack> monsterAttacks = new ArrayList<>();
        monsterAttacks.add(monsterAttack);
        monsterAttacks.add(mock(MonsterAttack.class));
        when(monsterAttackRepository.findAll()).thenReturn(monsterAttacks);
        List<MonsterAttack> newMonsterAttacks = monsterAttackService.findAll();
        for (MonsterAttack a : monsterAttacks) {
            assertTrue(newMonsterAttacks.contains(a));
        }
    }

    @Test
    void findById() {
        int id = monsterAttack.getId();
        when(monsterAttackRepository.findById(id)).thenReturn(Optional.of(monsterAttack));
        MonsterAttack newMonsterAttack = monsterAttackService.findById(id);
        assertEquals(monsterAttack,newMonsterAttack);
    }

    @Test
    void nullCheck() {
        assertThrows(ServiceException.class,
                ()-> monsterAttackService.nullCheck(Optional.empty(),"Empty Test"));
        assertEquals(monsterAttack,monsterAttackService.nullCheck(Optional.of(monsterAttack),"MonsterAttack Test"));
    }

    @Test
    void save() {
        when(monsterAttackRepository.save(monsterAttack)).thenReturn(monsterAttack);
        monsterAttackService.save(monsterAttack);
    }
}