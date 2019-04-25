package edu.se309.app.backend.rest.controller;

import edu.se309.app.backend.rest.entity.ConsumableItem;
import edu.se309.app.backend.rest.service.interfaces.ConsumableItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Consumable Item Controller
 */
@RestController
@RequestMapping("/api/items/consumable")
public class ConsumableItemController extends BaseController<ConsumableItem, Integer, ConsumableItemService> {

    /**
     * Constructor for this Controller
     *
     * @param consumableItemService Service associated with this controller
     */
    @Autowired
    public ConsumableItemController(ConsumableItemService consumableItemService) {
        super(consumableItemService);
    }
}