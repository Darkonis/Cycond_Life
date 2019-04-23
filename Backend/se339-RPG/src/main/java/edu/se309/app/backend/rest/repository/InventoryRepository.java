package edu.se309.app.backend.rest.repository;

import edu.se309.app.backend.rest.entity.Inventory;

import java.util.List;


public interface InventoryRepository extends BaseRepository<Inventory, Integer>{
	
	List<Inventory> findByPlayerId(int playerId);
}
