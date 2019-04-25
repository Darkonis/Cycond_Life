package edu.se309.app.backend.service;

import edu.se309.app.backend.rest.entity.ConsumableItem;
import edu.se309.app.backend.rest.repository.ConsumableItemRepository;
import edu.se309.app.backend.rest.service.ConsumableItemServiceImplementation;
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
class ConsumableItemServiceImplementationTest {

    @InjectMocks
    private ConsumableItemServiceImplementation consumableItemService;

    @Mock
    private ConsumableItemRepository consumableItemRepository;

    private ConsumableItem consumableItem;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        consumableItem = mock(ConsumableItem.class);
        consumableItemRepository = consumableItemService.getRepository();
    }

    @Test
    void count() {
        long expected = 1L;
        when(consumableItemRepository.count()).thenReturn(expected);
        long actual = consumableItemService.count();
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        doNothing().when(consumableItemRepository).deleteById(anyInt());
        consumableItemService.deleteById(consumableItem.getId());
    }

    @Test
    void findAll() {
        List<ConsumableItem> consumableItems = new ArrayList<>();
        consumableItems.add(consumableItem);
        consumableItems.add(mock(ConsumableItem.class));
        when(consumableItemRepository.findAll()).thenReturn(consumableItems);
        List<ConsumableItem> newConsumableItems = consumableItemService.findAll();
        for (ConsumableItem a : consumableItems) {
            assertTrue(newConsumableItems.contains(a));
        }
    }

    @Test
    void findById() {
        int id = consumableItem.getId();
        when(consumableItemRepository.findById(id)).thenReturn(Optional.of(consumableItem));
        ConsumableItem newConsumableItem = consumableItemService.findById(id);
        assertEquals(consumableItem, newConsumableItem);
    }

    @Test
    void nullCheck() {
        assertThrows(ServiceException.class,
                () -> consumableItemService.nullCheck(Optional.empty(), "Empty Test"));
        assertEquals(consumableItem, consumableItemService.nullCheck(Optional.of(consumableItem), "ConsumableItem Test"));
    }

    @Test
    void save() {
        when(consumableItemRepository.save(consumableItem)).thenReturn(consumableItem);
        consumableItemService.save(consumableItem);
    }
}