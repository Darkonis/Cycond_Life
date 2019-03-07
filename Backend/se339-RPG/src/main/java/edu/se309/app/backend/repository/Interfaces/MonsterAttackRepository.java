package edu.se309.app.backend.repository.Interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import edu.se309.app.backend.entity.MonsterAttack;

@Repository
public interface MonsterAttackRepository extends JpaRepository<MonsterAttack, Integer> 
{
	@Override
	List<MonsterAttack> findAll();
	
	@Override
	Optional<MonsterAttack> findById(Integer id);
	
}
