package edu.se309.app.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.se309.app.backend.entity.Monster;

@Repository
public interface MonstersRepository extends JpaRepository<Monster, Integer> {
		
		List<Monster> findAll();
		
		Monster save(Monster monster);
		
		Optional<Monster> findById(int id);
		
		void deleteAll();
		
}
