package edu.se309.app.backend.service.interfaces;

import java.util.List;
import java.util.Optional;

import edu.se309.app.backend.entity.Monster;

public interface MonsterService {
	
	void deleteAll();
	
	List<Monster> findAll();
	
	Optional<Monster> findById(int id);
	
	Monster save(Monster monster);
}
