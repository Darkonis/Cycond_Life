package edu.se309.app.backend.service.interfaces;

import edu.se309.app.backend.entity.UserStat;

public interface StatService extends BaseService<UserStat, Integer> {

  UserStat findByAccountId(int accountId);

  UserStat incrementByAmount(int accountId, String stat, int amount);

  UserStat incrementByOne(int accountId, String stat);

  UserStat updateUserStat(int accountId, String stat, int value);
  
  UserStat getByUsername(String username);  
  
}
