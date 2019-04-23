package edu.se309.app.backend.rest.service;

import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import edu.se309.app.backend.rest.entity.Inventory;
import edu.se309.app.backend.rest.repository.InventoryRepository;
import edu.se309.app.backend.rest.service.interfaces.InventoryService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class InventoryServiceImplementation extends BaseServiceImplementation<Inventory, Integer, InventoryRepository>
implements InventoryService {
	
	@Autowired
    public InventoryServiceImplementation(InventoryRepository inventoryRepository) {
        super(inventoryRepository);
    }
	
	@Override
	@Transactional
	@NonNull
	public List<Inventory> findByPlayerId(int playerId){
    	List<Inventory> result = new ArrayList();
    	List<Inventory> all = findAll();
    	for(int i = 0; i < all.size(); i++)
    	{
    		if(all.get(i).getPlayerId() == playerId)
    		{
    			result.add(all.get(i));
    		}
    	}
    	return result;
	}
}
