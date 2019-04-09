package edu.se309.app.backend.rest.service;

import edu.se309.app.backend.rest.entity.ConsumableItem;
import edu.se309.app.backend.rest.repository.ConsumableItemRepository;
import edu.se309.app.backend.rest.service.interfaces.ConsumableItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumableItemServiceImplementation extends
        BaseServiceImplementation<ConsumableItem, Integer, ConsumableItemRepository>
        implements ConsumableItemService {
    @Autowired
    public ConsumableItemServiceImplementation(ConsumableItemRepository consumableItemRepository) {
        super(consumableItemRepository);
    }
}
