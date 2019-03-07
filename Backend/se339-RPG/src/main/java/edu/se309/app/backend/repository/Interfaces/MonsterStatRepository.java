package edu.se309.app.backend.repository.Interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import edu.se309.app.backend.entity.MonsterStat;

@Repository
public interface MonsterStatRepository extends JpaRepository<MonsterStat, Integer> 
{
	@Override
	List<MonsterStat> findAll();
	
	
	@Override
	Optional<MonsterStat> findById(Integer id);
	
}