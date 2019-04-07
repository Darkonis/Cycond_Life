package edu.se309.app.backend.service;

import edu.se309.app.backend.entity.BaseItem;
import edu.se309.app.backend.repository.BaseItemRepository;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class BaseItemServiceImplementationTest {

    @InjectMocks
    private BaseItemServiceImplementation baseItemService;

    @Mock
    private BaseItemRepository baseItemRepository;

    private BaseItem baseItem;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        baseItem = mock(BaseItem.class);
        baseItemRepository = baseItemService.getRepository();
    }

    @Test
    void count() {
        long expected = 1L;
        when(baseItemRepository.count()).thenReturn(expected);
        long actual = baseItemService.count();
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        doNothing().when(baseItemRepository).deleteById(anyInt());
        baseItemService.deleteById(baseItem.getId());
    }

    @Test
    void findAll() {
        List<BaseItem> baseItems = new ArrayList<>();
        baseItems.add(baseItem);
        baseItems.add(mock(BaseItem.class));
        when(baseItemRepository.findAll()).thenReturn(baseItems);
        List<BaseItem> newBaseItems = baseItemService.findAll();
        for (BaseItem a : baseItems) {
            assertTrue(newBaseItems.contains(a));
        }
    }

    @Test
    void findById() {
        int id = baseItem.getId();
        when(baseItemRepository.findById(id)).thenReturn(Optional.of(baseItem));
        BaseItem newBaseItem = baseItemService.findById(id);
        assertEquals(baseItem, newBaseItem);

    }

    @Test
    void nullCheck() {
        assertThrows(ServiceException.class,
                () -> baseItemService.nullCheck(Optional.empty(), "Empty Test"));
        assertEquals(baseItem, baseItemService.nullCheck(Optional.of(baseItem), "BaseItem Test"));
    }

    @Test
    void save() {
        when(baseItemRepository.save(baseItem)).thenReturn(baseItem);
        baseItemService.save(baseItem);
    }
}