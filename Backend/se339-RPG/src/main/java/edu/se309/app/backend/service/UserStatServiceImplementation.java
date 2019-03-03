package edu.se309.app.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.entity.UserStat;
import edu.se309.app.backend.repository.Interfaces.UserStatRepository;
import edu.se309.app.backend.repository.Interfaces.UserStatRepositoryCustom;
import edu.se309.app.backend.service.interfaces.UserStatService;

@Service
public class UserStatServiceImplementation implements UserStatService {

	
	private UserStatRepository userStatRepository;
	private UserStatRepositoryCustom userStatRepositoryCustom;
	
	@Autowired
	public UserStatServiceImplementation(UserStatRepository userStatRepository, UserStatRepositoryCustom userStatRepositoryCustom) {
		this.userStatRepository = userStatRepository;
		this.userStatRepositoryCustom = userStatRepositoryCustom;		
	}
	
	@Override
	@Transactional
	public List<UserStat> findAll() {		
		return userStatRepository.findAll();
	}
	
	@Override
	@Transactional
	public UserStat findByAccountId(Integer accountId) {
		
		return userStatRepository.findByAccount(accountId);
	}
	

	@Override
	@Transactional
	public Optional<UserStat> findById(int userStatId) {
		
		return userStatRepository.findById(userStatId);
	}
	
	@Override
	@Transactional
	public void save(UserStat newUserStat) {
		userStatRepository.save(newUserStat);
	}
	
	

	
}
