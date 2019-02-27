package edu.se309.app.backend.monsterspawn;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



import edu.se309.app.backend.monsterspawn.Monsters;
import org.springframework.stereotype.Repository;

@Repository
public interface MonstersRepository extends JpaRepository<Monsters, Integer> {
		
		List<Monsters> findAll();
		
		Monsters save(Monsters monster);
		
		Optional<Monsters> findById(int id);
		
		void deleteAll();
		
}
