package edu.se309.app.backend.repository.interfaces;

import java.util.Optional;

import edu.se309.app.backend.entity.Monster;

public interface MonsterRepository extends BaseRepository<Monster, Integer> {

	@Override
	void deleteAll();

	Optional<Monster> findFirstByOrderByIdAsc();

}
