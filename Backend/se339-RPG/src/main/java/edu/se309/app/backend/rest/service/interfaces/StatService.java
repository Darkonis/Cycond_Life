package edu.se309.app.backend.rest.service.interfaces;

import edu.se309.app.backend.rest.entity.UserStat;

public interface StatService extends BaseService<UserStat, Integer> {


    UserStat incrementByAmount(int id, String stat, int amount);

    UserStat incrementByOne(int id, String stat);

    UserStat updateUserStat(int id, String stat, int value);

    UserStat getByUsername(String username);
}
