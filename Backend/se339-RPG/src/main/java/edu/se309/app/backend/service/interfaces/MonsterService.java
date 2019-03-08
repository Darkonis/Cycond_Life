package edu.se309.app.backend.service.interfaces;

import java.util.List;

import edu.se309.app.backend.entity.Monster;

public interface MonsterService {
	
	List<Monster> findAll();	
	void save(Monster monster);
	void deleteAll();

}
