package edu.se309.app.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.se309.app.backend.entity.Monster;
import edu.se309.app.backend.repository.Interfaces.MonstersRepository;
import edu.se309.app.backend.service.interfaces.MonsterService;

@Service
public class MonsterServiceImplementation implements MonsterService
{
	private MonstersRepository monsterRepository;
	
	@Autowired
	public MonsterServiceImplementation(MonstersRepository monsterRepository)
	{
		this.monsterRepository = monsterRepository;
	}
	
	@Override
	@Transactional
	public void deleteAll()
	{
		monsterRepository.deleteAll();
	}
	
	@Override
	@Transactional
	public List<Monster> findAll()
	{
		return monsterRepository.findAll();
	}
	
	@Override
	@Transactional
	public Optional<Monster> findById(int id)
	{
		return monsterRepository.findById(id);
	}
	
	@Override
	@Transactional
	public Monster save(Monster monster)
	{
		return monsterRepository.save(monster);
	}
}
