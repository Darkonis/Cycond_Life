package edu.se309.app.backend.rest.controller;

import edu.se309.app.backend.rest.entity.ConsumableItem;
import edu.se309.app.backend.rest.service.interfaces.ConsumableItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items/consumable")
public class ConsumableItemController extends BaseController<ConsumableItem, Integer, ConsumableItemService> {

    @Autowired
    public ConsumableItemController(ConsumableItemService consumableItemService) {
        super(consumableItemService);
    }
}