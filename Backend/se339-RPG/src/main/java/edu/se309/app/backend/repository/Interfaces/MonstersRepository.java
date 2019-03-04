package edu.se309.app.backend.repository.Interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.se309.app.backend.entity.Monster;

@Repository
public interface MonstersRepository extends JpaRepository<Monster, Integer> {
		
		@Override
		void deleteAll();
		
		@Override
		List<Monster> findAll();
		
		Optional<Monster> findById(int id);
		
		@Override
		Monster save(Monster monster);
		
}
