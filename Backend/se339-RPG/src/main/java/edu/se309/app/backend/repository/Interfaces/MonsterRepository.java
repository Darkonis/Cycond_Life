package edu.se309.app.backend.repository.Interfaces;

import edu.se309.app.backend.entity.Monster;

public interface MonsterRepository extends BaseRepository<Monster, Integer> {
		
		void deleteAll();		
		
}
