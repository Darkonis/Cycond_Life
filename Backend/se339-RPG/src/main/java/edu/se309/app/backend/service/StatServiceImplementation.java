package edu.se309.app.backend.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.se309.app.backend.entity.UserStat;
import edu.se309.app.backend.repository.Interfaces.UserStatRepository;
import edu.se309.app.backend.service.interfaces.StatService;

@Service
public class StatServiceImplementation implements StatService {

	private UserStatRepository userStatRepository;
	

	@Autowired
	public StatServiceImplementation(UserStatRepository userStatRepository) {
		this.userStatRepository = userStatRepository;
		
	}

	@Override
	@Transactional
	public List<UserStat> findAll() {		
		return userStatRepository.findAll();
	}

	@Override
	@Transactional
	public void save(UserStat newUserStat) {
		userStatRepository.save(newUserStat);
		
	}

	@Override
	@Transactional
	public UserStat updateUserStat(int accountId, String stat, int value) {
		UserStat userStat = findByAccountId(accountId);		
		PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(userStat);
		return setStatValue(myAccessor, userStat, stat, value);
	}
	
	@Override
	@Transactional
	public UserStat incrementByAmount(int accountId, String stat, int amount) {
		UserStat userStat = findByAccountId(accountId);		
		PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(userStat);
		return setStatValue(myAccessor, userStat, stat, ((Integer)myAccessor.getPropertyValue(stat)) + amount);
		
	}
	private UserStat setStatValue(PropertyAccessor myAccessor, UserStat userStat, String stat, int value) {
		if(isStat(stat) && myAccessor.isReadableProperty(stat) && myAccessor.getPropertyType(stat) == int.class) {
			myAccessor.setPropertyValue(stat, value);
			save(userStat);
			return userStat;
		} else {
			throw new ServiceException("Invalid request: No such stat found: " + stat);
		}
	}
	
	private boolean isStat (String stat) {
		if (stat.equals("statId") || stat.equals("account")) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public UserStat incrementByOne(int accountId, String stat) {
		return incrementByAmount(accountId,stat,1);		
	}

	@Override
	@Transactional
	@NonNull
	public UserStat findByAccountId(int accountId) {	
		Optional<UserStat> userStat = userStatRepository.findByAccountId(accountId);
		if (userStat.isEmpty()) {
			throw new ServiceException("Invalid request: No such account found with Id: " + accountId);
		} else {
			return userStat.get();
		}
	}
	@Override
	@Transactional
	public long count() {
		return userStatRepository.count();
	}
}


