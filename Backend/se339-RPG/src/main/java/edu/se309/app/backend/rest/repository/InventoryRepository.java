package edu.se309.app.backend.rest.repository;

import edu.se309.app.backend.rest.entity.Inventory;

import java.util.List;


public interface InventoryRepository extends BaseRepository<Inventory, Integer>{

	/**
	 * Find player's inventory by ID
	 * @param playerId player's ID
	 * @return inventory
	 */
	List<Inventory> findByPlayerId(int playerId);
}
