package edu.se309.app.backend.rest.service.interfaces;

import edu.se309.app.backend.rest.entity.Inventory;
import java.util.List;

public interface InventoryService extends BaseService<Inventory, Integer> {
	
	List<Inventory> findByPlayerId(int playerId);

}
