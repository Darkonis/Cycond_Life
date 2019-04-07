package edu.se309.app.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.se309.app.backend.entity.ConsumableItem;
import edu.se309.app.backend.repository.ConsumableItemRepository;
import edu.se309.app.backend.service.interfaces.ConsumableItemService;

@Service
public class ConsumableItemServiceImplementation  extends 
	BaseServiceImplementation<ConsumableItem, Integer, ConsumableItemRepository> 
	implements ConsumableItemService{
	@Autowired
	public ConsumableItemServiceImplementation(ConsumableItemRepository consumableItemRepository){
		super(consumableItemRepository);
	}
}
