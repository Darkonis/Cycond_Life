package edu.se309.app.backend.repository.interfaces;

import java.util.Optional;

import edu.se309.app.backend.entity.Monster;

public interface MonsterRepository extends BaseRepository<Monster, Integer> {
		
		void deleteAll();

		Optional<Monster> findFirstByOrderByIdAsc();		
		
}
