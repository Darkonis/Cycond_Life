package edu.se309.app.backend.monsterspawn;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import edu.se309.app.backend.monsterspawn.monsters;
import org.springframework.stereotype.Repository;

@Repository
public interface MonstersRepository extends JpaRepository<monsters, Integer> {
		
		List<monsters> findAll();
		
		monsters save(monsters monster);
}
