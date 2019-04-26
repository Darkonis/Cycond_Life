package edu.se309.app.backend.rest.controller;

import edu.se309.app.backend.rest.entity.Inventory;
import edu.se309.app.backend.rest.service.interfaces.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Inventory Controller
 */
@RestController
@RequestMapping("/api/inventory")
public class InventoryController extends BaseController<Inventory, Integer, InventoryService> {

    /**
     * Constructor for this Controller
     *
     * @param inventoryService Service associated with this controller
     */
    @Autowired
    public InventoryController(InventoryService inventoryService) {
        super(inventoryService);
    }

    /**
     * Find the inventory of a user by Username
     *
     * @param id id of user
     * @return Inventory of the user
     */
    @GetMapping("byUserId/{id}")
    public List<Inventory> getInventoryByUsername(@PathVariable int id) {
        return getService().findByPlayerId(id);
    }
    
    @PostMapping("/add")
    public Inventory addInventory(@RequestBody Inventory inventory){
    	inventory.setId(0);
    	getService().save(inventory);
    	return inventory;
    	}
}
