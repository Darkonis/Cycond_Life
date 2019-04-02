package edu.se309.app.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.se309.app.backend.entity.BaseItem;
import edu.se309.app.backend.repository.BaseItemRepository;
import edu.se309.app.backend.service.interfaces.BaseItemService;

@Service
public class BaseItemServiceImplementation extends 
	BaseServiceImplementation<BaseItem, Integer, BaseItemRepository> implements BaseItemService{
	
	@Autowired
	public BaseItemServiceImplementation(BaseItemRepository baseItemRepository){
		super(baseItemRepository);
	}
}
